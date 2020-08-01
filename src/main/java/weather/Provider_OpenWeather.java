/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather;

//If serious issues arise.
import common.CriticalException;

import java.net.URI;

//network abstraction (for http)
import net.NetFetch;
import net.NoResponseException;

//JSON parser library
import org.json.JSONObject;
import org.json.JSONException;


//collections for mapping OpenWeather's weathercodes to WeatherStatus
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author neido
 */

/*
Implementation of the Provider interface that uses OpenWeatherMap online
services as a backend.
This backend requires an OpenWeather API key. Whatever application uses it
must have a means of retrieving such key.
*/
public class Provider_OpenWeather implements Provider {
 
    //last JSON String fetched.
    //might be used for caching in a future revision.
    String JSONReport;
    
    NetFetch http;
    String domainURL;
    String appid; //mandatory OpenWeather API key (one per user, registration required)
    //insert weather IDs here(check openweather API documentation for the codes)
    private Map<Integer, WeatherStatus> ConditionCodes_Coarse;//coarse codes (5xx=raining, 8xx=cloudy)
    private Map<Integer, WeatherStatus> ConditionCodes_Fine;
    
    //NOTE TO SELF:
    //PLEASE, FOR YOUR OWN GOOD
    //DO NOT INCLUDE SENSITIVE DATA ON THE PROJECT.
    //THIS INCLUDES API KEYS.
    //DO NOT COMMIT WITH API KEYS
    //DO NOT COMMIT WITH API KEYS
    //DO NOT COMMIT WITH API KEYS
    public Provider_OpenWeather(String keys){
        //start structure that maps condition codes to weatherStatus
        mapConditionCodes();
        
        //prepare http
        this.http=new NetFetch();
        
        //base URL for JSON API requests to openWeather
        this.domainURL="https://api.openweathermap.org/data/2.5/onecall?";
        
	//Your OpenWeather API key goes here
	this.appid=keys;
	
    }
    
    /*INTERNAL HELPER FUNCTIONS
	These methods are meant to allow reuse and to split long
	portions of code into smaller, properly identified chunks.
    */
    
    /*Initialize the condition code hashmap for use by ComputeStatus()*/
    private void mapConditionCodes(){
        Map<Integer, WeatherStatus> coarse=new HashMap<>();
        coarse.put(5, WeatherStatus.RAINING);
        coarse.put(8, WeatherStatus.CLOUDY);
        this.ConditionCodes_Coarse=coarse;
    }
    
    /*translate weather id (Condition Code as per OpenWeather documentation)
    into a reference to its corresponding WeatherStatus.
    More information on condition codes at this link
    https://openweathermap.org/weather-conditions#Weather-Condition-Codes-2
    */
    private WeatherStatus computeStatus(int conditionCode){
       /*for some reason, 'clear skies' is assigned a number in the same
	8xx region as the 'cloudy'-like weather codes.
	perhaps they thought 'clear skies' is just
	'cloudy' with no clouds.
	*/
        if(conditionCode==800){
	    return WeatherStatus.CLEAR;
	}
	else{
	    /*not all conditions are currently implemented.
	    some of them might not ever be supported.*/
	    if((conditionCode/100)!=5 && (conditionCode/100)!=8){
		System.err.println("ConditionCode not implemented or invalid: nº"+conditionCode);
		return WeatherStatus.UNDEFINED;
	    }
	    //take only 3rd digit into account; pass it through a map
	    return this.ConditionCodes_Coarse.get(conditionCode/100);
	}
    }
    
    
    /*'Provider' ROLE
    
    */
    
    /*
    Get JSON out of an HTTP request to OpenWeather, parse JSON into internal
    WeatherLog representation and provide the WeatherLog.
    */
    @Override
    public WeatherLog getWeather(Location loc) 
	    throws WeatherUnavailableException, CriticalException{
	try{
	    //get JSON online as string
	    String rawJSON=http.fetchString(domainURL+"lat="+loc.getLatitude()
		+"&lon="+loc.getLongitude()
		+"&appid="+this.appid);
	    this.JSONReport=rawJSON;
	    
	    //turn string into proper JSON object
	    JSONObject json=new JSONObject(rawJSON);
	    
	    //traversal: current weather(JSON:this.current)
	    json=json.getJSONObject("current");//weather for current time
	    
	    //get temperature from JSON.
	    //(JSON:this.current.temp)
	    double temp=json.getDouble("temp");
	    
	    //decode the overall condition of the skies as an ID;
	    //convert that ID into an internal WeatherStatus representation.
	    //(JSON: current.weather[0].id)
	    WeatherStatus id=computeStatus(
		    json.getJSONArray("weather")
		    .getJSONObject(0)
		    .getInt("id"));
	    
	    //finally, pack it into a WeatherLog and return the new instance.
	    return new MinimalLog(id, temp);
	}
	
	//failure modes (may include: offline, interrupted, invalid, etc.)
	//Caller might want to retry a few times, just in case
        catch(NoResponseException |JSONException e ){
	    throw new WeatherUnavailableException(e);
        }
	catch(CriticalException e){
	    throw e;
	}
    }
    
    
    
    /*METHODS FOR TESTING
    
    */
    
    //json object parse test
    public String JSONEntryPoint(String input){
	JSONObject json=new JSONObject(input);
	return "temperature is " + json.getDouble("current.temp")
		+"\ncurrent ID is " + json.getInt("current.weather.0.id")
		;
    }
    
    //get last JSON fetched as a string
    public String getJSON(){
	return this.JSONReport;
    }
    
    
}

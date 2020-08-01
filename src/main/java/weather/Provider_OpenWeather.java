/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather;

import common.CriticalException;

import java.net.URI;

import net.NetFetch;
import net.NoResponseException;

//JSON parser library
import org.json.JSONObject;
import org.json.JSONException;


//collections for mapping OpenWeather's weathercodes to weatherStatus
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author neido
 */
public class Provider_OpenWeather implements Provider {
 
    String JSONReport;
    double temperature;
    WeatherStatus status;
    Location zone;
    
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
        
        //default location for requests(São Paulo)
        this.zone= new Location(-23.533773,-46.625290);
        
	//Your OpenWeather API key goes here
	this.appid=keys;
	
    }
    
    
    private void mapConditionCodes(){
        Map<Integer, WeatherStatus> coarse=new HashMap<>();
        coarse.put(5, WeatherStatus.RAINING);
        coarse.put(8, WeatherStatus.CLOUDY);
        this.ConditionCodes_Coarse=coarse;
    }
    
    public double getTemperature(){
        return this.temperature;
    }
    //TODO: implement hashmap conversion for MORE conditionCodes
    private WeatherStatus computeStatus(int conditionCode){
       //get it from json object
        if(conditionCode==800){
	    return WeatherStatus.CLEAR;
	}
	else{
	    if((conditionCode/100)!=5 && (conditionCode/100)!=8){
		System.err.println("ConditionCode not implemented or invalid: nº"+conditionCode);
		return WeatherStatus.UNDEFINED;
	    }
	    return this.ConditionCodes_Coarse.get(conditionCode/100);
	}
        //return this.ConditionCodes_Coarse.get()
    }
    
    @Override
    public WeatherLog getWeather(Location loc) 
	    throws WeatherUnavailableException, CriticalException{
	 //fetch JSON for weather via single-call API
	
	
	try{
	    //if json fetch succeeds, parse data into internal WeatherLog object
	    String rawJSON=http.fetchString(domainURL+"lat="+loc.getLatitude()
		+"&lon="+loc.getLongitude()
		+"&appid="+this.appid);
	    this.JSONReport=rawJSON;
	    JSONObject json=new JSONObject(rawJSON);

	    json=json.getJSONObject("current");//weather for current time
	    double temp=json.getDouble("temp");
	    //JSON nested object traversal: current.weather[0].id
	    WeatherStatus id=computeStatus(
		    json.getJSONArray("weather")
		    .getJSONObject(0)
		    .getInt("id"));
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
    
    public void setZone(Location fresh){
	this.zone=fresh;
    }
    
    
    //test functions (please remove after it's confirmed working)
    
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

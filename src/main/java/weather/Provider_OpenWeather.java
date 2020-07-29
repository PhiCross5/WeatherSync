/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather;

import java.net.Authenticator;
import java.net.URI;
import java.time.LocalDateTime;

//Java built-in HTTP client for fetching the JSON Weather report
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

//JSON parser library
import org.json.JSONObject;

//exceptions
import java.io.IOException;

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
    
    HttpClient service;
    String requestURL;
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
        service=HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        
        //base URL for JSON API requests to openWeather
        this.requestURL="https://api.openweathermap.org/data/2.5/onecall?";
        
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
    //TODO: implement hashmap conversion for conditionCodes
    private WeatherStatus computeStatus(int conditionCode){
       //get it from json object
        if(conditionCode==800){
	    return WeatherStatus.CLEAR;
	}
	else{
	    if((((int)conditionCode/100)!=5) 
		    && (((int)conditionCode/100)!=8)){
		System.out.println("ConditionCode not implemented yet: nº"+conditionCode);
	    }
	    return this.ConditionCodes_Coarse.get(conditionCode/100);
	}
        //return this.ConditionCodes_Coarse.get()
    }
    
    
    public WeatherLog getWeather() throws WeatherUnavailableException{
        //fetch JSON for weather via single-call API
	
	//build HTTP request
	HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestURL 
                        + "lat="+zone.getLatitude()
                        + "&lon="+zone.getLongitude()
                        + "&appid="+this.appid))
                .build();
        //send HTTP request
	try{
	//if json fetch succeeds, parse data into internal WeatherLog object
        String rawJSON=service.send(request, HttpResponse.BodyHandlers.ofString()).body();
        this.JSONReport=rawJSON;
	JSONObject json=new JSONObject(rawJSON);
	
	json=json.getJSONObject("current");//weather for current time
	double temp=json.getDouble("temp");
	//current.weather[0].id
	WeatherStatus id=computeStatus(
		json.getJSONArray("weather")
		.getJSONObject(0)
		.getInt("id"));
	return new MinimalLog(id, temp);
	}
	//failure modes (may include: offline, interrupted, invalid, etc.)
        catch(IOException | InterruptedException io){
	    System.out.println("FAILURE: could not fetch JSON online!");
            System.out.println(io);
	    throw new WeatherUnavailableException();
        }
    }
    
    public WeatherLog getWeather(Location loc)throws WeatherUnavailableException{
	try{
	    zone=loc;
	    return getWeather();
	}
	catch(WeatherUnavailableException e){
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

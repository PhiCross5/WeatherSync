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
    
    public Provider_OpenWeather(){
        //start structure that maps condition codes to weatherStatus
        mapConditionCodes();
        
        //prepare http
        service=HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(10))
                .authenticator(Authenticator.getDefault())
                .build();
        
        //base URL for JSON API requests to openWeather
        requestURL="https://api.openweathermap.org/data/2.5/onecall?";
        
        //default location for requests(São Paulo)
        zone= new Location(-23.533773,-46.625290);
        
    }
    
    private void mapConditionCodes(){
        Map<Integer, WeatherStatus> coarse=new HashMap<>();
        coarse.put(5, WeatherStatus.RAINING);
        coarse.put(8, WeatherStatus.CLOUDY);
        this.ConditionCodes_Coarse=coarse;
    }
    
    public double getTemperature(){
        return 0.0;
    }
    //TODO: implement hashmap conversion for conditionCodes
    private WeatherStatus computeStatus(int conditionCode){
       //get it from json object
        if(conditionCode==800){
	    return WeatherStatus.SUNNY;
	}
	else{
	    if((conditionCode/100)!=5 | (conditionCode/100)!=8){
		System.out.print("ConditionCode not implemented yet: nº"+conditionCode);
	    }
	    return this.ConditionCodes_Coarse.get(conditionCode/100);
	}
        //return this.ConditionCodes_Coarse.get()
    }
    
    
    public WeatherLog getWeather(){
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
        JSONObject json=new JSONObject(rawJSON);
	
	json=json.getJSONObject("current");
	double temp=json.getDouble("temp");
	WeatherStatus id=computeStatus(json.getInt("weather.id"));
	return new MinimalLog(id, temp);
	}
	//failure modes (may include: offline, interrupted, invalid, etc.)
        catch(IOException | InterruptedException io){
            System.out.println(io);
        }
        
        return null;
    }
    
}

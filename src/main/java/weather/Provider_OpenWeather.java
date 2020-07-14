/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather;

import java.time.LocalDateTime;

import java.net.http.HttpClient;

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
    
    //insert weather IDs here(check openweather API documentation for the codes)
    private Map<Integer, WeatherStatus> ConditionCodes_Coarse;//coarse codes (5xx=raining, 8xx=cloudy)
    private Map<Integer, WeatherStatus> ConditionCodes_Fine;
    
    public Provider_OpenWeather(){
        mapConditionCodes();
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
    public WeatherStatus getStatus(){
        return WeatherStatus.SUNNY;
    }
    
    
    public WeatherLog getWeather(){
        return null;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather;

import java.time.LocalDateTime;
import common.CriticalException;

/**
 *
 * @author yaichao
 */
public interface Provider {
    
    //How is the weather right here, right now.
    //If Provider is not aware of time or place, the service may
    //use geolocation and its own timekeeping facilities to determine
    //time and place.
    /*
    public abstract WeatherLog getWeather() 
	    throws WeatherUnavailableException, CriticalException;
    */
    //returns object with the weather for the specified point in time.
    //-may be historical (how it was) or a forecast(how it might be)
   // public abstract WeatherLog getWeather(LocalDateTime time);
    
    //NOT USED RIGHT NOW.
    //time and place for everything.
   // public abstract WeatherLog getWeather(LocalDateTime time, Location place);
    
    public abstract WeatherLog getWeather(Location loc) 
	    throws WeatherUnavailableException, CriticalException;
	
    
}

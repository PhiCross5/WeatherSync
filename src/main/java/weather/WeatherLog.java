/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather;

import java.time.LocalDateTime;

/**
 *
 * @author yaichao
 */

/**
WeatherLog is the interface of a set of weather information encapsulated as parameters in an object.
 such parameters refer to weather in a given instant.
 * if different interpretations are required, such as weather forecast for a span of
 * 6 hours or over a whole day, those can be constructed from multiple logs of 
 * different instants.
**/
public interface WeatherLog {
 
    public abstract WeatherStatus getStatus();
    
    public abstract double getTemperature();
    
   
    
}

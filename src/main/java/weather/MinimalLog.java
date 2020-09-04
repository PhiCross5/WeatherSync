/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather;

/**
 *
 * @author neido
 */

/**MinimalLog is the least common denominator among encapsulated weather data
 objects. It is simple to integrate, carries only the most used parameters
  and has a consistent API.**/
public class MinimalLog implements WeatherLog{
    WeatherStatus status;
    double temperature;
    
    public MinimalLog(WeatherStatus st, double tp){
        status=st;
        temperature=tp;
    }
        /**gets an object representing the weather in a simplified, descriptive way
	 (e.g. raining, cloudy, clear skies...)**/
    public WeatherStatus getStatus(){
        return status;
    }
    
    /**gets the temperature as a numerical value (units in Kelvin).
     **/
    public double getTemperature(){
        return temperature;
    }
    
    /** gets a string that textually summarizes the all weather information in
     this log.**/
    public String toString(){
	return "MinimalLog{\nstatus: "+status
		+"\ntemperature: "+temperature+"\n}";
    }
}

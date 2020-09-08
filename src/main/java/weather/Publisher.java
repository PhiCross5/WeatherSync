/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather;


import common.CriticalException;
/**
 *
 * @author neido
 */

/**
 * 
 * Publisher maintains references to previously created WeatherLog objects
 * and updates their inner data on demand.
 **/
public interface Publisher {
    
    public abstract void updateWeather(WeatherLog log, Location loc)
	    throws WeatherUnavailableException, CriticalException; 
    
}

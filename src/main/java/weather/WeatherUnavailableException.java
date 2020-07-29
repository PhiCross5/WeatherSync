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
public class WeatherUnavailableException extends Exception {
    
    public String toString(){
	return "could not get valid weather data";
    }
    public WeatherUnavailableException(Exception e){
	super(e);
    }
}

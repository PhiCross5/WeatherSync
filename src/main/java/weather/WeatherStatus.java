/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather;

/**
 *
 * @author yaichao 
 */

/*ABOUT THIS CLASS:
    WeatherStatus represents the weather in a qualitative manner.
The objects hard-coded below define fundamental states of weather such as "sunny"
or "raining".
    Simpler applications may use only the status, ommiting less frequently used
parameters of a full-fledged model for convenience.
*/
public class WeatherStatus {
    private String description;

    public static WeatherStatus instance=new WeatherStatus();
    
    public WeatherStatus CLEAR;
    public WeatherStatus CLOUDY;
    public WeatherStatus RAINING;
    public WeatherStatus UNDEFINED;
    
    public static WeatherStatus getInstance(){
	return instance;
    }
    
    public String toString(){
        return description;
    }
    private WeatherStatus(String description){
        this.description=description;
    }
    private WeatherStatus(){
	CLEAR= new WeatherStatus("clear skies");
	CLOUDY=new WeatherStatus("cloudy");
	RAINING=new WeatherStatus("raining");
	UNDEFINED=new WeatherStatus("*undefined*");
    }
    
}

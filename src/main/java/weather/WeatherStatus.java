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
    String description;
    
    public String toString(){
        return description;
    }
    private WeatherStatus(String description){
        this.description=description;
    }
    public static WeatherStatus CLEAR= new WeatherStatus("clear skies");
    public static WeatherStatus CLOUDY=new WeatherStatus("cloudy");
    public static WeatherStatus RAINING=new WeatherStatus("raining");
   
}

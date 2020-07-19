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
public class MinimalLog implements WeatherLog{
    WeatherStatus status;
    double temperature;
    
    public MinimalLog(WeatherStatus st, double tp){
        status=st;
        temperature=tp;
    }
    public WeatherStatus getStatus(){
        return status;
    }
    
    public double getTemperature(){
        return temperature;
    }
    
    public String toString(){
	return "MinimalLog{\nstatus:"+status
		+"\ntemperature:"+temperature;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;
import weather.WeatherStatus;
import weather.WeatherLog;
import weather.MinimalLog;
import weather.Provider;
import weather.Provider_OpenWeather;

import confidential.SuperSecretData;
/**
 *
 * @author neido
 */
public class OpenWeather_fetch {
    public static void main(String[] args) {
	
	/*
	WeatherLog log = new MinimalLog(WeatherStatus.SUNNY, 295);
	System.out.println(log);
	*/
	
	//put your api key here
	Provider_OpenWeather provider=new Provider_OpenWeather
	    (SuperSecretData.apiKey);
	
	//dump WeatherLog and Source JSON
	
	
    }
}

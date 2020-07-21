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

//only for devs!
//import confidential.SuperSecretData;
/**
 *
 * @author neido
 */
public class run {
    public static void main(String[] args) {
	double latitude;
	double longitude;
	String apiKey="";
	if(args.length>0){
	    apiKey=args[0];
	    latitude=Double.valueOf(args[1]);
	    longitude=Double.valueOf(args[2]);
	}
	else{
	    System.out.println("usage: run (api key) (latitude) (longitude)");
	    return;
	}
	
	/*
	WeatherLog log = new MinimalLog(WeatherStatus.SUNNY, 295);
	System.out.println(log);
	*/
	
	//put your api key here
	Provider_OpenWeather provider=new Provider_OpenWeather
	    (apiKey);
	
	//dump WeatherLog and Source JSON
	WeatherLog sauce=provider.getWeather();
	String doubleCheck=provider.getJSON();
	System.out.println("**weather provided**");
	System.out.println(sauce.getTemperature()-273.15 +"ºC, "
	+"and it is " + sauce.getStatus());
	System.out.println("**raw JSON**");
	System.out.println(doubleCheck);
	
    }
}

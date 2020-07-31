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
import weather.WeatherUnavailableException;
import confidential.SuperSecretData;
import weather.Location;
/**
 *
 * @author neido
 */
public class OpenWeather_fetch {
    
    //a static inner class with coordinates for some cities.
   static class places{
	public static Location SAO_PAULO=new Location(-23.533773,-46.625290);
	public static Location SALVADOR=new Location(-12.9711100,-38.5108300);
	public static Location NEW_YORK=new Location( 40.7142700,-74.0059700);
	public static Location TORONTO=new Location(43.7001100,-79.4163000);
	
   }
    
    
    public static void main(String[] args) {
	
	double latitude;
	double longitude;
	String apiKey;
	if(args.length>0){
	    apiKey=args[0];
	    latitude=Double.valueOf(args[1]);
	    longitude=Double.valueOf(args[2]);
	}
	else{
	    apiKey=SuperSecretData.apiKey;
	}
	
	/*
	WeatherLog log = new MinimalLog(WeatherStatus.SUNNY, 295);
	System.out.println(log);
	*/
	
	//put your api key here
	Provider_OpenWeather provider=new Provider_OpenWeather
	    (SuperSecretData.apiKey);
	
	//dump WeatherLog and Source JSON
	try{
	    WeatherLog sauce=provider.getWeather(places.SALVADOR);
	    String doubleCheck=provider.getJSON();
	    System.out.println("**weather provided**");
	    System.out.println("temperature:"+(sauce.getTemperature()-273.15)
		    +"ºC, "
	    +"\nstatus:" + sauce.getStatus());
	    System.out.println("\nraw JSON:\n"+doubleCheck);
	}
	catch(WeatherUnavailableException e){
	    System.err.println("Congratulations, you have failed!");
	    System.err.println("details below:\n"+e);
	}
    }
}

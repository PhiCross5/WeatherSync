/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.io.IOException;
import java.lang.InterruptedException;

import confidential.SuperSecretData;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import org.json.JSONException;

/**
 *
 * @author neido
 */
public class Provider_OpenWeatherTest {
    
    private String key=SuperSecretData.apiKey;
    
    public Provider_OpenWeatherTest() {
    }

    //first check if there is an internet connection.
    //don't test anything if host is offline
    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
	HttpClient srv=HttpClient.newBuilder()
	    .version(HttpClient.Version.HTTP_1_1)
	    .connectTimeout(Duration.ofSeconds(10))
	    .build();
	try{
	    srv.send(HttpRequest.newBuilder().GET()
		    .uri(new URI("https://www.google.com"))
		    .build(), 
		    BodyHandlers.ofString()).body();
	}
	catch(IOException | InterruptedException e){
	    System.out.println("HTTP won't work! check your internet connection.");
	    System.err.println("HTTP problem! details:\n"+e);
	    System.exit(1);
	}
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
	
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetWeather(){
	System.out.println("Testing weather fetch");
	Provider_OpenWeather provider=new Provider_OpenWeather(this.key);
	try{
	    provider.getWeather();
	}
	catch(WeatherUnavailableException e){
	    System.err.println("could not get weather online");
	    fail();
	}
	catch(JSONException je){
	    System.err.println("JSON parsing issues");
	    fail();
	}
    }
    
}
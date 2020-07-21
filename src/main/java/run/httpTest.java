/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.io.IOException;
import java.lang.InterruptedException;
import java.net.URI;
import java.nio.charset.Charset;
import java.time.Duration;
/**
 *
 * @author neido
 */
public class httpTest {
    public static void main(String[] args) {
	//make http client
	HttpClient http=HttpClient.newBuilder()
		.connectTimeout(Duration.ofSeconds(10))
		.version(HttpClient.Version.HTTP_2)
		.build();
	
	//make http request
	HttpRequest req=HttpRequest.newBuilder(URI.create("http://www.pudim.com.br"))
		.GET()
		.build();
	try{
	    String output=http.send(req, 
		    HttpResponse.BodyHandlers.ofString(Charset.forName("UTF-8")))
		    .body();
	    System.out.println(output);
	}
	catch(IOException | InterruptedException f){
	    System.out.println("Http problem!");
	    System.out.println(f);
	    return;
	}
    }
}

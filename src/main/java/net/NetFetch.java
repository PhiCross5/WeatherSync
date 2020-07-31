/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package net;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import java.io.IOException;
import java.nio.charset.Charset;

import common.CriticalException;
/**
 *
 * @author neido
 */

public class NetFetch {
    
    HttpClient client;
    HttpRequest req;
    
    //sane defaults for an HTTP client interface.
    public NetFetch(){
	client=HttpClient.newBuilder()
		.version(HttpClient.Version.HTTP_2)
		.connectTimeout(Duration.ofSeconds(30))
		.build();
	req=HttpRequest.newBuilder()
		.GET()
		.build();
		}
    
    //switch domains
    public void updateRequest(String req){
	
    }
    
    //get a String as a resource described by a URL.
    public String fetchString(String url)throws UnreachableException, CriticalException{
	try{
	    HttpRequest request=HttpRequest.newBuilder()
		    .GET()
		    .uri(new URI(url))
		    .build();
	String reply=client.send(req,
		HttpResponse.BodyHandlers.ofString(Charset.forName("UTF-8")))
		.body();
	return reply;
	}
	
	/*some unforeseen situations may lead to the HTTP subsystem
	not acquiring any response. 
	*/
	catch(IOException | InterruptedException e){
	    throw new UnreachableException(e);
	}
	
	/* malformed Uniform Resource Identifiers mean serious business.
	This should absolutely not happen.
	*/
	catch(URISyntaxException e){
	    throw new CriticalException(e);
	}
	
    }
    
    //place-holder stub.
    public byte[] fetchBinary(String url) throws UnreachableException, CriticalException{
	try{
	    HttpRequest request=HttpRequest.newBuilder()
		    .GET()
		    .uri(new URI(url))
		    .build();
	byte[]reply=client.send(request,
		HttpResponse.BodyHandlers.ofByteArray())
		.body();
	return reply;
	}
	catch(IOException | InterruptedException e){
	    throw new UnreachableException(e);
	}
	catch(URISyntaxException e){
	    throw new CriticalException(e);
	}
    }
    
    
    
    
}

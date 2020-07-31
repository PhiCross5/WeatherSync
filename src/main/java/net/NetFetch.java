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
    
    //sane defaults for an HTTP client interface.
    public NetFetch(){
	client=HttpClient.newBuilder()
		.version(HttpClient.Version.HTTP_2)
		.connectTimeout(Duration.ofSeconds(30))
		.build();
		}
    
    //switch domains
    public void updateRequest(String req){
	
    }
    
    //get a String as a resource described by a URL.
    public String fetchString(String url)throws NoResponseException, CriticalException{
	try{
	    HttpRequest request=HttpRequest.newBuilder()
		    .GET()
		    .uri(new URI(url))
		    .build();
	String reply=client.send(request,
		HttpResponse.BodyHandlers.ofString(Charset.forName("UTF-8")))
		.body();
	return reply;
	}
	
	/*
	Sometimes the host system may not have a working internet connection 
	or the servers might be offline. This will trigger an exception 
	that the caller must handle.
	*/
	catch(IOException | InterruptedException e){
	    throw new NoResponseException(e);
	}
	
	/* All callers in this package provide hard-coded strings
	as an argument, thus if the URI is wrong once,
	it will bewrong everytime.
	This should absolutely not happen.
	*/
	catch(URISyntaxException e){
	    throw new CriticalException(e);
	}
	
    }
    
    
    /*get some resource online as a binary stream.
	this lower level method is a last resort and should be avoided
    */
    public byte[] fetchBinary(String url) throws NoResponseException, CriticalException{
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
	    throw new NoResponseException(e);
	}
	catch(URISyntaxException e){
	    throw new CriticalException(e);
	}
    }
    
    
    
    
}

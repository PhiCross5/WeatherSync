/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

/**
 *
 * @author neido
 */

/*This Exception is raised when the NetFetch could not acquire a given resource
online.
Requests to get data through internet are not garanteed to succeed,
thus the caller must be prepared for cases where there is no response.
*/
public class NoResponseException extends Exception{
    public NoResponseException(){
	super();
    }
    public NoResponseException(Throwable cause){
	super(cause);
    }
    public String toString(){
	return "unable to fetch resource online";
    }
}

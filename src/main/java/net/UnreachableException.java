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
public class UnreachableException extends Exception{
    public UnreachableException(){
	super();
    }
    public UnreachableException(Throwable cause){
	super(cause);
    }
    public String toString(){
	return "unable to fetch resource online";
    }
}

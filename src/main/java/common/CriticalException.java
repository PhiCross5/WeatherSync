/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author neido
 */
public class CriticalException extends Exception{
    
    public CriticalException(){
	super();
    }
    public CriticalException(Throwable cause){
	super(cause);
    }
    
    public String toString(){
	return "Something went terribly wrong.";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import net.NetFetch;
import net.NoResponseException;
import common.CriticalException;

/**
 *
 * @author neido
 */
public class httpString {
    public static void main(String[] args){
	NetFetch http=new NetFetch();
	try{
	    String dumplers=http.fetchString("https://www.whoami.com");
	    System.out.println("text acquired: \n"+dumplers);
	}
	catch(NoResponseException e){
	    System.err.println("could not get content online");
	}
	catch(CriticalException e){
	    System.err.println("plain wrong URL! try something else.");
	}
    }
}

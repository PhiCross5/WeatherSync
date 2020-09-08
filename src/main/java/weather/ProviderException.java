/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather;

/**
 *
 * @author neido
 */

/**
 Generic exception for things that go wrong in the provider.
 **/
public class ProviderException extends Exception{
    public String toString(){
	return "something went wrong in the Provider.";
    }
}

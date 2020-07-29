/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

import org.json.JSONObject;
/**
 *
 * @author neido
 */


/*
This class is meant as a test for the JSON library used in the project.
it samples (presumably) valid json from a file and prints out the relevant pieces
used by the project through a JSONObject instance.
*/
public class JSON_Print {
    
    public static void main(String[] args){
	//Please pick a magic number big enough for any 
	//  OpenWeather Single-Call JSON Response.
	//hopefully 100K Chars will suffice.
	char[] jsonBytes=new char[100000];
	
	
	//attempt to load JSON from fileSystem
	//note: root directory for file path may vary according to platform
	try{
	new FileReader("resources/OneCallSample1.json", Charset.forName("UTF-8")).read(jsonBytes);
	}
	catch(FileNotFoundException f){
	    System.out.println("rather unfortunate.");
	    System.out.println(f);
	    return;
	}
	catch(IOException f){
	    System.out.println("oh noes!");
	    System.out.println(f);
	    return;
	}
	
	
	//Build string from char array; print the string for inspection
	String JSON_Dummy=new String(jsonBytes);
	//System.out.println("json dump: " + JSON_Dummy);
	//System.out.flush();
	
	
	//Build JSON object from String
	JSONObject json=new JSONObject(JSON_Dummy);
	//Fetch data from data(syntax is important here)
	System.out.println("\n\nJSON REPORT"
		+"\n-temperature is "
		+json.getJSONObject("current")
		    .getDouble("temp") +"K"
		+"\n-weather code is "
		+json.getJSONObject("current")
		    .getJSONArray("weather")
		    .getJSONObject(0)
			.getInt("id")
	);
    }
}

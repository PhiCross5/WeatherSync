/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;
import weather.WeatherStatus;
/**
 *
 * @author yaichao
 */
public class PrintAvaliableStatuses {
    public static void main(String args[]){
	WeatherStatus status=WeatherStatus.getInstance();
        System.out.print(status.CLEAR);
    }
}

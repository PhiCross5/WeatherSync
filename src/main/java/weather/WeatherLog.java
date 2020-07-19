/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather;

import java.time.LocalDateTime;

/**
 *
 * @author yaichao
 */
public interface WeatherLog {
 
    public abstract WeatherStatus getStatus();
    
    public abstract double getTemperature();
    
    
    
}

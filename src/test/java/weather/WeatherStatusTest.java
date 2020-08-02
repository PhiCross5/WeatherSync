/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author neido
 */
public class WeatherStatusTest {
    
    static WeatherStatus status;
    public WeatherStatusTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
	status=WeatherStatus.getInstance();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    private void checkStatusStrings(WeatherStatus source, String expected){
	assertArrayEquals(source.toString().toCharArray(),
		   expected.toCharArray());
    }
    
    @Test
    public void weather_cloudy() {
	checkStatusStrings(status.CLOUDY, "cloudy");
    }
    
    @Test
    public void weather_clear() {
	checkStatusStrings(status.CLEAR,"clear skies");
    }
    
    @Test
    public void weather_raining() {
	checkStatusStrings(status.RAINING,"raining");
    }
    
    @Test
    public void weather_undefined() {
	checkStatusStrings(status.UNDEFINED,"undefined");
    }
}

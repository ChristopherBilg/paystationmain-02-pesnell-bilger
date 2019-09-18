/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import paystation.domain.rates.LinearRateStrategy;
import paystation.domain.rates.RateStrategy;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Paul
 */
public class LinearRateStrategyTest {
    
    RateStrategy rate;
    
    @BeforeClass
    public static void BeforeAllTests() {
        
    }
    
    @AfterClass
    public static void CleanUp() {
        
    }
    
    @Before
    public void beforeEach() {
        rate = new LinearRateStrategy();
    }
    
    @Test
    public void zeroReturnsZero() {
        assertEquals("No money should return no time", 0, rate.calculateTime(0));
    }
    
    @Test
    public void fiveReturnsTwo() {
        assertEquals("One nickel returns two minutes", 2, rate.calculateTime(5));
    }
}

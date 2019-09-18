/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import paystation.domain.rates.ProgressiveRateStrategy;
import paystation.domain.rates.RateStrategy;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;

public class ProgressiveRateStrategyTest {
    
    RateStrategy rate;
    
    @Before
    public void beforeEach() {
        rate = new ProgressiveRateStrategy();
    }
    
    // The following two test cases will be for the first progressive rate
    @Test
    public void ZeroReturnsZero() {
        assertEquals("No money should return no time", 0, rate.calculateTime(0));
    }
    
    @Test
    public void FiveReturnsTwo() {
        assertEquals("One nickel returns two minutes", 2, rate.calculateTime(5));
    }
    
    // The following two test cases will be for the second progressive rate
    @Test
    public void TwoHundredReturnsSeventyFive() {
        assertEquals("200 cents should equal 75 minutes", 75, rate.calculateTime(200));
    }
    
    @Test
    public void ThreeHundredReturnsOneHundredAndFive() {
        assertEquals("300 cents should equal 105 minutes", 105, rate.calculateTime(300));
    }
    
    // The following two test cases will be for the third, and final, progressive rate
    @Test
    public void FourHundredReturnsOneHundredAndThirty() {
        assertEquals("400 cents should equal 130 minutes", 130, rate.calculateTime(400));
    }
    
    @Test
    public void FiveHundredReturnsOneHundredAndFifty() {
        assertEquals("500 cents should equal 150 minutes", 150, rate.calculateTime(500));
    }
    
}

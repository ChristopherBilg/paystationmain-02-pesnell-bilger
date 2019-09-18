/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import paystation.domain.rates.RateStrategy;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import paystation.domain.rates.AlternatingRateStrategy;

public class AlternatingRateStrategyTest {
    
    RateStrategy rateWeekday;
    RateStrategy rateWeekend;
    
    @Before
    public void beforeEach() {
        rateWeekday = new AlternatingRateStrategy(true);
        rateWeekend = new AlternatingRateStrategy(false);
    }
    
    // The following two test cases will be for weekday == true
    @Test
    public void ZeroReturnsZero() {
        assertEquals("No money should return no time", 0, rateWeekday.calculateTime(0));
    }
    
    @Test
    public void TenReturnsFour() {
        assertEquals("One nickel returns two minutes", 2, rateWeekday.calculateTime(5));
    }
    
    // The following three test cases will be for weekday == false
    // One will be for each "branch" of the progressive rate strategy
    @Test
    public void FiveReturnsTwo() {
        assertEquals("One nickel returns two minutes", 2, rateWeekend.calculateTime(5));
    }
    
    @Test
    public void TwoHundredReturnsSeventyFive() {
        assertEquals("200 cents should equal 75 minutes", 75, rateWeekend.calculateTime(200));
    }
    
    @Test
    public void FourHundredReturnsOneHundredAndThirty() {
        assertEquals("400 cents should equal 130 minutes", 130, rateWeekend.calculateTime(400));
    }
    
}

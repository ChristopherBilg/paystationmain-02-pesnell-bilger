/**
 * Testcases for the Pay Station system.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
package paystation.domain;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

public class PayStationImplTest {

    PayStation ps;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setup() {
        ps = new PayStationImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Entering 5 cents should make the display report 2 minutes parking time.
     */
    @Test
    public void shouldDisplay2MinFor5Cents()
            throws IllegalCoinException {
        ps.addPayment(5);
        assertEquals("Should display 2 min for 5 cents",
                2, ps.readDisplay());
    }

    /**
     * Entering 25 cents should make the display report 10 minutes parking time.
     */
    @Test
    public void shouldDisplay10MinFor25Cents() throws IllegalCoinException {
        ps.addPayment(25);
        assertEquals("Should display 10 min for 25 cents",
                10, ps.readDisplay());
    }

    /**
     * Verify that illegal coin values are rejected.
     */
    @Test(expected = IllegalCoinException.class)
    public void shouldRejectIllegalCoin() throws IllegalCoinException {
        ps.addPayment(17);
    }

    /**
     * Entering 10 and 25 cents should be valid and return 14 minutes parking
     */
    @Test
    public void shouldDisplay14MinFor10And25Cents()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals("Should display 14 min for 10+25 cents",
                14, ps.readDisplay());
    }

    /**
     * Buy should return a valid receipt of the proper amount of parking time
     */
    @Test
    public void shouldReturnCorrectReceiptWhenBuy()
            throws IllegalCoinException {
        ps.addPayment(5);
        ps.addPayment(10);
        ps.addPayment(25);
        Receipt receipt;
        receipt = ps.buy();
        assertNotNull("Receipt reference cannot be null",
                receipt);
        assertEquals("Receipt value must be 16 min.",
                16, receipt.value());
    }

    /**
     * Buy for 100 cents and verify the receipt
     */
    @Test
    public void shouldReturnReceiptWhenBuy100c()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(25);
        ps.addPayment(25);

        Receipt receipt;
        receipt = ps.buy();
        assertEquals(40, receipt.value());
    }

    /**
     * Verify that the pay station is cleared after a buy scenario
     */
    @Test
    public void shouldClearAfterBuy()
            throws IllegalCoinException {
        ps.addPayment(25);
        ps.buy(); // I do not care about the result
        // verify that the display reads 0
        assertEquals("Display should have been cleared",
                0, ps.readDisplay());
        // verify that a following buy scenario behaves properly
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals("Next add payment should display correct time",
                14, ps.readDisplay());
        Receipt r = ps.buy();
        assertEquals("Next buy should return valid receipt",
                14, r.value());
        assertEquals("Again, display should be cleared",
                0, ps.readDisplay());
    }

    /**
     * Verify that cancel clears the pay station
     */
    @Test
    public void shouldClearAfterCancel()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.cancel();
        assertEquals("Cancel should clear display",
                0, ps.readDisplay());
        ps.addPayment(25);
        assertEquals("Insert after cancel should work",
                10, ps.readDisplay());
    }






    
    
    /**
     * Verify that the canceled entry does not add to the amount returned by
     * empty.
     * @throws Exception 
     */
    @Test
    public void cancelShouldNotAddToEmpty() throws Exception
    {
        PayStationImpl instance = new PayStationImpl();
        int amountAdded = 25;
        instance.addPayment(amountAdded);
        instance.cancel();
        instance.addPayment(amountAdded);
        int result = instance.empty();
        assertEquals(amountAdded, result);
        
    }
    
    /**
     * Very that the empty method resets the total to zero.
     * @throws Exception 
     */
    @Test
    public void testEmptyZero() throws Exception
    {
        PayStationImpl instance = new PayStationImpl();
        int amountAdded = 10;
        instance.addPayment(amountAdded);
        instance.empty();
        int result = instance.empty();
        assertEquals(0, result);
    }
    
    /**
     * Verify that cancel returns a map with the correct amount of coins for one
     * coin type.
     * @throws Exception 
     */
    @Test
    public void test1CoinMapReturn() throws Exception
    {
        PayStationImpl instance = new PayStationImpl();
        int amountAdded = 25;
        instance.addPayment(amountAdded);
        int result = instance.cancel().get(3);
        assertEquals(1, result);
    }
    
    /**
     * Verify that cancel returns a map with the correct amount of coins for
     * all coin types.
     * @throws Exception 
     */
    @Test
    public void testNoCoinMapReturn() throws Exception
    {
        PayStationImpl instance = new PayStationImpl();
        int quarter = 25;
        int dime = 10;
        int nickle = 5;
        //instance.addPayment(quarter);
        instance.addPayment(dime);
        instance.addPayment(dime);
        instance.addPayment(nickle);
        Map answer = new HashMap();
        //answer.put(1, 1);
        answer.put(2, 2);
        answer.put(1, 1);
        Map result = instance.cancel();
        assertEquals(answer, result);
    }
    
    /**
     * Verify that cancel returns a map without keys when no coins are entered.
     * @throws Exception 
     */
    @Test
    public void testMultipleCoinMapReturn() throws Exception
    {
        PayStationImpl instance = new PayStationImpl();
        int quarter = 25;
        int dime = 10;
        int nickle = 5;
        instance.addPayment(dime);
        instance.addPayment(dime);
        instance.addPayment(quarter);
        instance.addPayment(nickle);
        Map answer = new HashMap();
        answer.put(1, 1);
        answer.put(2, 2);
        answer.put(3, 1);
        Map result = instance.cancel();
        assertEquals(answer, result);
    }
 
    /**
     * Verify that cancel clears the map.
     * @throws Exception 
     */
    @Test
    public void testCancelClearMap() throws Exception
    {
        PayStationImpl instance = new PayStationImpl();
        int quarter = 25;
        int dime = 10;
        int nickle = 5;
        instance.addPayment(quarter);
        instance.addPayment(nickle);
        instance.addPayment(dime);
        instance.addPayment(quarter);
        instance.cancel();
        Map result = instance.cancel();
        Map emptyMap = new HashMap();
        assertEquals(emptyMap, result);
    }
    
    /**
     * Verify that buy clears the map.
     * @throws Exception 
     */
    @Test
    public void testBuyClearMap() throws Exception
    {
        PayStationImpl instance = new PayStationImpl();
        int quarter = 25;
        int dime = 10;
        int nickle = 5;
        instance.addPayment(quarter);
        instance.addPayment(nickle);
        instance.addPayment(dime);
        instance.addPayment(quarter);
        instance.buy();
        Map result = instance.cancel();
        Map emptyMap = new HashMap();
        assertEquals(emptyMap, result);
    }
    
    
}

package paystation.domain;
import java.util.*;

/**
 * Implementation of the pay station.
 *
 * Responsibilities:
 *
 * 1) Accept payment; 
 * 2) Calculate parking time based on payment; 
 * 3) Know earning, parking time bought; 
 * 4) Issue receipts; 
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class PayStationImpl implements PayStation {
    
    private int insertedSoFar;
    private int timeBought;
    private Map coinMap = new HashMap();
    private boolean nickelBool = false;
    private boolean dimeBool = false;
    private boolean quarterBool = false;
    
    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {

        switch (coinValue) {
            case 5:
                if(!nickelBool)
                {
                    coinMap.put(1, 1);
                    nickelBool = true;
                }
                else
                {
                    coinMap.put(1, (int)coinMap.get(1) + 1);
                    
                }
                break;
            case 10:
                if(!dimeBool)
                {
                    coinMap.put(2, 1);
                    dimeBool = true;
                }
                else
                {
                    coinMap.put(2, (int)coinMap.get(2) + 1);
                }
                break;
            case 25:
                if(!quarterBool)
                {
                    coinMap.put(3, 1);
                    quarterBool = true;
                }
                else
                {
                    coinMap.put(3, (int)coinMap.get(3) + 1);
                }
                break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        insertedSoFar += coinValue;
        timeBought = insertedSoFar / 5 * 2;
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        r.showOnDisplay();
        reset();
        return r;
    }

    @Override
    public Map<Integer, Integer> cancel() 
    {
        Map tempMap = new HashMap();
        tempMap.putAll(coinMap);
        reset();
        
        // Show on display the map of coins as well as the total coin value
        showCancelValuesOnDisplay(tempMap);
        
        return tempMap;
    }
    
    private void reset() {
        timeBought = insertedSoFar = 0;
        nickelBool = false;
        dimeBool = false;
        quarterBool = false;
        coinMap.clear();
    }
    
    @Override
    public int empty()
    {
        int total = insertedSoFar;
        insertedSoFar = 0;
        return total;
    }
    
    private void showCancelValuesOnDisplay(Map map) {
        int nickelAmount = (int)map.get(1);
        int dimeAmount = (int)map.get(2);
        int quarterAmount = (int)map.get(3);
        int totalAmount = (nickelAmount * 5)
                + (dimeAmount * 10)
                + (quarterAmount * 25);
        
        System.out.println("** Cancelled ** Amount Returned:");
        System.out.println("Nickels: $" + nickelAmount);
        System.out.println("Dime: $" + dimeAmount);
        System.out.println("Quarter: $" + quarterAmount);
        System.out.println("Total Amount: $" + totalAmount);
    }
}

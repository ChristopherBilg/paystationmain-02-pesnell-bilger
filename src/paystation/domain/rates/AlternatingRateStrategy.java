/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain.rates;

public class AlternatingRateStrategy implements RateStrategy {
    
    private boolean weekday;
    
    public AlternatingRateStrategy(boolean weekday) {
        this.weekday = weekday;
    }
    
    @Override
    public int calculateTime(int moneyInserted) {
        if(weekday) {
            return (moneyInserted * 2) / 5;
        }
        else {
            if (moneyInserted < 150)
                return (moneyInserted * 2) / 5;
            else if(moneyInserted < 350)
                return ((moneyInserted - 150) * 3 / 10) + 60;
            else
                return ((moneyInserted - 350) / 5) + 120;
        }
    }
    
}

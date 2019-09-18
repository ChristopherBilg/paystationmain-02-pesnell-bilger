/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain.rates;

public class ProgressiveRateStrategy implements RateStrategy {

    @Override
    public int calculateTime(int moneyInserted) {
        if (moneyInserted < 150)
            return (moneyInserted * 2) / 5;
        else if(moneyInserted < 350)
            return ((moneyInserted - 150) * 3 / 10) + 60;
        else
            return ((moneyInserted - 350) / 5) + 120;
    }
    
}

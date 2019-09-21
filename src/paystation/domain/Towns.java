/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import paystation.domain.rates.AlternatingRateStrategy;
import paystation.domain.rates.LinearRateStrategy;
import paystation.domain.rates.ProgressiveRateStrategy;
import paystation.domain.rates.RateStrategy;

/**
 *
 * @author Paul
 */

public enum Towns {
    DEFAULT(new LinearRateStrategy()),
    ALPHATOWN(new LinearRateStrategy()),
    BETATOWN(new ProgressiveRateStrategy()),
    GAMMATOWN(new AlternatingRateStrategy(true));
    
    private RateStrategy rs;
    
    private Towns(RateStrategy r) {
        rs = r;
    }
    
    public RateStrategy getRate() {
        return rs;
    }
}

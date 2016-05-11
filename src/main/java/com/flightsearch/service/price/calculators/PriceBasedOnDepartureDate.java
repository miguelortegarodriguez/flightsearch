package com.flightsearch.service.price.calculators;

import com.flightsearch.service.price.PriceContext;

public class PriceBasedOnDepartureDate implements Calculator {

    @Override
    public Double apply(PriceContext priceContext, Double accumulatedPrice) {
        int daysToDeparture = priceContext.daysToDeparture;
        if(daysToDeparture > 30) {
            return accumulatedPrice * 0.8;
        } else if(daysToDeparture > 15 && daysToDeparture <= 30) {
            return accumulatedPrice;
        } else if(daysToDeparture > 2 && daysToDeparture <= 15) {
            return accumulatedPrice * 1.2;
        } else {
            return accumulatedPrice * 1.5;
        }
    }
}

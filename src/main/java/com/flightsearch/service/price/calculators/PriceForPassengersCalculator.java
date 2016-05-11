package com.flightsearch.service.price.calculators;

import com.flightsearch.service.price.PriceContext;

public class PriceForPassengersCalculator implements Calculator {

    @Override
    public Double apply(PriceContext priceContext, Double accumulatedPrice) {
        return priceContext.numberOfPassengers * accumulatedPrice;
    }
}

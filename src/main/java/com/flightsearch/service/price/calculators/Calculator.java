package com.flightsearch.service.price.calculators;

import com.flightsearch.service.price.PriceContext;

import java.util.function.BiFunction;

public interface Calculator extends BiFunction<PriceContext, Double, Double> {

    @Override
    Double apply(PriceContext priceContext, Double accumulatedPrice);

}

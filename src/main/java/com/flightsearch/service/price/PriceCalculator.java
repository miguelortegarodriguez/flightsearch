package com.flightsearch.service.price;

import com.flightsearch.api.PassengerRequest;
import com.flightsearch.api.SearchRequest;
import com.flightsearch.model.Flight;
import com.flightsearch.service.price.calculators.Calculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class PriceCalculator {

    private List<Calculator> calculators;

    public PriceCalculator(List<Calculator> calculators) {
        this.calculators = calculators;
    }

    public Double calculatePrice(Flight flight, SearchRequest searchRequest) {
        return round(searchRequest.passengers
                        .stream()
                        .map(p -> priceForPassengers(p, flight, searchRequest.daysToDeparture))
                        .collect(Collectors.summingDouble(p -> p))
        );
    }

    private Double priceForPassengers(PassengerRequest passengerRequest, Flight flight, int daysToDeparture) {
        PriceContext context = new PriceContext(
                flight,
                passengerRequest.passengerType,
                daysToDeparture,
                passengerRequest.number
        );

        Double price = flight.price;
        for (Calculator calculator : calculators) {
            price = calculator.apply(context, price);
        }
        return BigDecimal.valueOf(price)
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }

    private Double round(Double price) {
        return BigDecimal.valueOf(price)
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }

}

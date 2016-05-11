package com.flightsearch.service.price.calculators;

import com.flightsearch.model.Airline;
import com.flightsearch.repository.AirlineRepository;
import com.flightsearch.service.price.PriceContext;

public class PriceBasedOnPassengerTypeCalculator implements Calculator {

    private final AirlineRepository airlineRepository;

    public PriceBasedOnPassengerTypeCalculator(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Override
    public Double apply(PriceContext priceContext, Double accumulatedPrice) {
        switch (priceContext.passengerType) {
            case ADULT:
                return accumulatedPrice;
            case CHILD:
                return accumulatedPrice * 0.67;
            case INFANT:
                return getAirline(priceContext).infantPrice;
            default:
                throw new RuntimeException("Passenger type " + priceContext.passengerType + " not recognized");
        }
    }

    private Airline getAirline(PriceContext priceContext) {
        return airlineRepository
                .findAirlineByIataCode(priceContext.flight.getAirlineCode())
                .orElseThrow(
                        () -> new RuntimeException(
                                "Airline not found for IATA code " + priceContext.flight.getAirlineCode())
                );
    }

}

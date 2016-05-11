package com.flightsearch.service.price;

import com.flightsearch.model.Flight;
import com.flightsearch.model.PassengerType;

public class PriceContext {

    public final Flight flight;
    public final PassengerType passengerType;
    public final int daysToDeparture;
    public final int numberOfPassengers;

    public PriceContext(Flight flight, PassengerType passengerType, int daysToDeparture, int numberOfPassengers) {
        this.flight = flight;
        this.passengerType = passengerType;
        this.daysToDeparture = daysToDeparture;
        this.numberOfPassengers = numberOfPassengers;
    }
}

package com.flightsearch.api;

import com.flightsearch.model.PassengerType;

public class PassengerRequest {

    public final int number;
    public final PassengerType passengerType;

    public PassengerRequest(int number, PassengerType passengerType) {
        this.number = number;
        this.passengerType = passengerType;
    }
}

package com.flightsearch.api;

import java.util.List;

public class SearchRequest {

    public final List<PassengerRequest> passengers;
    public final int daysToDeparture;
    public final String origin;
    public final String destination;

    public SearchRequest(List<PassengerRequest> passengers, int daysToDeparture, String origin, String destination) {
        this.passengers = passengers;
        this.daysToDeparture = daysToDeparture;
        this.origin = origin;
        this.destination = destination;
    }
}

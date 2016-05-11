package com.flightsearch.model;

public class Flight {

    public final String origin;
    public final String destination;
    public final String code;
    public final Double price;

    public Flight(String origin, String destination, String code, Double price) {
        this.origin = origin;
        this.destination = destination;
        this.code = code;
        this.price = price;
    }

    public String getAirlineCode() {
        return code.substring(0, 2);
    }

}

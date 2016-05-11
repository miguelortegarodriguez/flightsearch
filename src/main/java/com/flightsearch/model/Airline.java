package com.flightsearch.model;

public class Airline {

    public final String iataCode;
    public final String name;
    public final Double infantPrice;

    public Airline(String iataCode, String name, Double infantPrice) {
        this.iataCode = iataCode;
        this.name = name;
        this.infantPrice = infantPrice;
    }
}

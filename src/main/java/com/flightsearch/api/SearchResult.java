package com.flightsearch.api;

public class SearchResult {

    public final String flightCode;
    public final Double price;

    public SearchResult(String flightCode, Double price) {
        this.flightCode = flightCode;
        this.price = price;
    }
}

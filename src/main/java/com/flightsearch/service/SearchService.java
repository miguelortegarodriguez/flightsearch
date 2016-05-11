package com.flightsearch.service;


import com.flightsearch.api.SearchRequest;
import com.flightsearch.api.SearchResult;
import com.flightsearch.model.Flight;
import com.flightsearch.repository.FlightRepository;
import com.flightsearch.service.price.PriceCalculator;

import java.util.List;
import java.util.stream.Collectors;

public class SearchService {

    private final FlightRepository flightRepository;
    private final PriceCalculator priceCalculator;


    public SearchService(FlightRepository flightRepository, PriceCalculator priceCalculator) {
        this.flightRepository = flightRepository;
        this.priceCalculator = priceCalculator;
    }

    public List<SearchResult> searchFlights(SearchRequest searchRequest) {
        return flightRepository.findFlightsByOriginAndDestination(searchRequest.origin, searchRequest.destination)
                .stream()
                .map(f -> createResult(f, searchRequest))
                .collect(Collectors.toList());
    }

    private SearchResult createResult(Flight flight, SearchRequest searchRequest) {
        return new SearchResult(flight.code, priceCalculator.calculatePrice(flight, searchRequest));
    }

}

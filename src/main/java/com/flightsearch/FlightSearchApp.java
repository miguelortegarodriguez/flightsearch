package com.flightsearch;

import com.flightsearch.api.SearchRequest;
import com.flightsearch.api.SearchResult;
import com.flightsearch.repository.AirlineRepository;
import com.flightsearch.repository.FlightRepository;
import com.flightsearch.repository.impl.CsvAirlineRepository;
import com.flightsearch.repository.impl.CsvFlightRepository;
import com.flightsearch.service.SearchService;
import com.flightsearch.service.price.PriceCalculator;
import com.flightsearch.service.price.calculators.Calculator;
import com.flightsearch.service.price.calculators.PriceBasedOnDepartureDate;
import com.flightsearch.service.price.calculators.PriceBasedOnPassengerTypeCalculator;
import com.flightsearch.service.price.calculators.PriceForPassengersCalculator;

import java.util.Arrays;
import java.util.List;

public class FlightSearchApp {

    private final SearchService searchService;

    public FlightSearchApp() {
        AirlineRepository airlineRepository = new CsvAirlineRepository("airlines.csv");
        Calculator priceForPassengers = new PriceForPassengersCalculator();
        Calculator priceBasedOnDeparture = new PriceBasedOnDepartureDate();
        Calculator priceBasedOnPassengerType = new PriceBasedOnPassengerTypeCalculator(airlineRepository);
        List<Calculator> calculators = Arrays.asList(priceBasedOnDeparture, priceBasedOnPassengerType, priceForPassengers);
        PriceCalculator priceCalculator = new PriceCalculator(calculators);
        FlightRepository flightRepository = new CsvFlightRepository("flights.csv");
        this.searchService = new SearchService(flightRepository, priceCalculator);
    }

    public List<SearchResult> searchFlights(SearchRequest searchRequest) {
        return searchService.searchFlights(searchRequest);
    }

}

package com.flightsearch.service;

import com.flightsearch.api.PassengerRequest;
import com.flightsearch.api.SearchRequest;
import com.flightsearch.api.SearchResult;
import com.flightsearch.model.Flight;
import com.flightsearch.model.PassengerType;
import com.flightsearch.repository.FlightRepository;
import com.flightsearch.service.price.PriceCalculator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private PriceCalculator priceCalculator;

    private SearchService searchService;

    private String origin = "AMS";
    private String destination = "FRA";
    private PassengerRequest passenger = new PassengerRequest(1, PassengerType.ADULT);
    private SearchRequest request = new SearchRequest(Collections.singletonList(passenger), 7, origin, destination);

    @Before
    public void setUp() {
        searchService = new SearchService(flightRepository, priceCalculator);
    }

    @Test
    public void should_return_an_empty_list_if_the_repository_does_not_return_flights() {
        when(flightRepository.findFlightsByOriginAndDestination(origin, destination))
                .thenReturn(Collections.emptyList());
        List<SearchResult> flights = searchService.searchFlights(request);
        assertTrue(flights.isEmpty());
    }

    @Test
    public void should_return_the_flights_returned_by_the_flight_repository_and_the_price_from_price_calculator() {
        String flightCode = "IB244242";
        Double resultPrice = 4.32;
        Flight flight = new Flight(origin, destination, flightCode, 12.3);
        when(flightRepository.findFlightsByOriginAndDestination(origin, destination))
                .thenReturn(Collections.singletonList(flight));
        when(priceCalculator.calculatePrice(flight, request))
                .thenReturn(resultPrice);
        List<SearchResult> flights = searchService.searchFlights(request);
        assertEquals(flightCode, flights.get(0).flightCode);
        assertEquals(resultPrice, flights.get(0).price);
    }

}

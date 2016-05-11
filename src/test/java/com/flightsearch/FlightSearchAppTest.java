package com.flightsearch;

import com.flightsearch.api.PassengerRequest;
import com.flightsearch.api.SearchRequest;
import com.flightsearch.api.SearchResult;
import com.flightsearch.model.PassengerType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class FlightSearchAppTest {

    private FlightSearchApp app;

    @Before
    public void setUp() {
        app = new FlightSearchApp();
    }

    @Test
    public void one_adult_30_days_to_departure_AMS_FRA() {
        PassengerRequest passenger = new PassengerRequest(1, PassengerType.ADULT);
        SearchRequest request = new SearchRequest(Collections.singletonList(passenger), 30, "AMS", "FRA");
        List<SearchResult> flights = app.searchFlights(request);
        assertEquals(3, flights.size());
        assertContainsFlight(flights, "TK2372", 197.0);
        assertContainsFlight(flights, "TK2659", 248.0);
        assertContainsFlight(flights, "LH5909", 113.0);
    }

    @Test
    public void two_adults_1_child_1_infant_15_days_to_departure_LHR_IST() {
        PassengerRequest adults = new PassengerRequest(2, PassengerType.ADULT);
        PassengerRequest child = new PassengerRequest(1, PassengerType.CHILD);
        PassengerRequest infant = new PassengerRequest(1, PassengerType.INFANT);
        SearchRequest request = new SearchRequest(Arrays.asList(adults, child, infant), 15, "LHR", "IST");
        List<SearchResult> flights = app.searchFlights(request);
        assertEquals(2, flights.size());
        assertContainsFlight(flights, "TK8891", 806.0);
        assertContainsFlight(flights, "LH1085", 481.19);
    }

    @Test
    public void one_adult_2_children_2_days_to_departure_BCN_MAD() {
        PassengerRequest adult = new PassengerRequest(1, PassengerType.ADULT);
        PassengerRequest children = new PassengerRequest(2, PassengerType.CHILD);
        SearchRequest request = new SearchRequest(Arrays.asList(adult, children), 2, "BCN", "MAD");
        List<SearchResult> flights = app.searchFlights(request);
        assertEquals(2, flights.size());
        assertContainsFlight(flights, "IB2171", 909.09);
        assertContainsFlight(flights, "LH5496", 1028.43);
    }

    @Test
    public void one_adult_1_days_to_departure_CDG_FRA() {
        PassengerRequest adult = new PassengerRequest(1, PassengerType.ADULT);
        SearchRequest request = new SearchRequest(Collections.singletonList(adult), 2, "CDG", "FRA");
        List<SearchResult> flights = app.searchFlights(request);
        assertEquals(0, flights.size());
    }

    private void assertContainsFlight(List<SearchResult> flights, String flightCode, Double price) {
        assertTrue(flights.stream().anyMatch(f -> f.flightCode.equals(flightCode) && f.price.equals(price)));
    }

}

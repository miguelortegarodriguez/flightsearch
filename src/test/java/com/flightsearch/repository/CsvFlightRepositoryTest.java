package com.flightsearch.repository;

import com.flightsearch.model.Flight;
import com.flightsearch.repository.impl.CsvFlightRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class CsvFlightRepositoryTest {

    private FlightRepository flightRepository;

    @Before
    public void setUp() {
        flightRepository = new CsvFlightRepository("flights.csv");
    }

    @Test
    public void search_flights_from_madrid_to_madrid_should_return_empty_result() {
        List<Flight> result = flightRepository.findFlightsByOriginAndDestination("MAD", "MAD");
        assertEquals(0, result.size());
    }

    @Test
    public void search_flights_from_madrid_to_rome_should_return_one_result() {
        List<Flight> result = flightRepository.findFlightsByOriginAndDestination("MAD", "FCO");
        assertEquals(1, result.size());
        assertTrue(result.stream().anyMatch(f -> f.code.equals("LH8408") && f.price == 149));
    }

    @Test
    public void search_flights_from_copenhagen_to_frankfurt_should_return_two_results() {
        List<Flight> result = flightRepository.findFlightsByOriginAndDestination("CPH", "FRA");
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(f -> f.code.equals("IB2818") && f.price == 186));
        assertTrue(result.stream().anyMatch(f -> f.code.equals("LH1678") && f.price == 298));
    }

}

package com.flightsearch.repository;

import com.flightsearch.model.Airline;
import com.flightsearch.repository.impl.CsvAirlineRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class CsvAirlineRepositoryTest {

    private AirlineRepository airlineRepository;

    @Before
    public void setUp() {
        airlineRepository = new CsvAirlineRepository("airlines.csv");
    }

    @Test
    public void find_airline_iberia_should_return_the_correct_object(){
        Optional<Airline> result = airlineRepository.findAirlineByIataCode("IB");
        assertTrue(result.isPresent());
        assertEquals("Iberia", result.get().name);
        assertTrue(result.get().infantPrice == 10);
    }

    @Test
    public void find_airline_foo_should_return_empty_value(){
        Optional<Airline> result = airlineRepository.findAirlineByIataCode("FOO");
        assertTrue(!result.isPresent());
    }

}

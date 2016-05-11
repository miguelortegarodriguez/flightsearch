package com.flightsearch.repository;

import com.flightsearch.model.Airline;

import java.util.Optional;

public interface AirlineRepository {

    Optional<Airline> findAirlineByIataCode(String iataCode);

}

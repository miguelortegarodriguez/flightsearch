package com.flightsearch.repository;

import com.flightsearch.model.Flight;

import java.util.List;

public interface FlightRepository {

    List<Flight> findFlightsByOriginAndDestination(String origin, String destination);

}

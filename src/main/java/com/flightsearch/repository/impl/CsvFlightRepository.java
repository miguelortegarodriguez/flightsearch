package com.flightsearch.repository.impl;

import com.flightsearch.model.Flight;
import com.flightsearch.repository.FlightRepository;
import com.flightsearch.util.CsvReader;

import java.util.List;
import java.util.stream.Collectors;

public class CsvFlightRepository implements FlightRepository {

    private List<Flight> flights;

    public CsvFlightRepository(String csvFlightsFile) {
        flights = CsvReader.readCsv(csvFlightsFile, this::csvLineToFlight).collect(Collectors.toList());
    }

    @Override
    public List<Flight> findFlightsByOriginAndDestination(String origin, String destination) {
        return flights.stream()
                .filter(f -> f.origin.equals(origin) && f.destination.equals(destination))
                .collect(Collectors.toList());
    }

    private Flight csvLineToFlight(String[] csvLine) {
        return new Flight(csvLine[0], csvLine[1], csvLine[2], Double.valueOf(csvLine[3]));
    }

}

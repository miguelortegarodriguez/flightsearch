package com.flightsearch.repository.impl;

import com.flightsearch.model.Airline;
import com.flightsearch.repository.AirlineRepository;
import com.flightsearch.util.CsvReader;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CsvAirlineRepository implements AirlineRepository {

    private List<Airline> airlines;

    public CsvAirlineRepository(String csvAirlinesPath) {
        airlines = CsvReader.readCsv(csvAirlinesPath, this::csvLineToAirline).collect(Collectors.toList());
    }

    @Override
    public Optional<Airline> findAirlineByIataCode(String iataCode) {
        return airlines.stream()
                .filter(a -> a.iataCode.equals(iataCode))
                .findFirst();
    }

    private Airline csvLineToAirline(String[] csvLine) {
        return new Airline(csvLine[0], csvLine[1], Double.valueOf(csvLine[2]));
    }

}

Flight Search [![Build Status](https://travis-ci.org/miguelortegarodriguez/flightsearch.svg?branch=master)](https://travis-ci.org/miguelortegarodriguez/flightsearch)
=============

The entry Point of the app is the FlightSearchApp object, where all the dependencies are injected.

A search is represented by the SearchRequest object and the result is a list of SearchResult objects.

The SearchService is the responsible of orchestrate the logic of finding the flights and calculate the price calling to the FlightRepository and the PriceCalculator.

The current implementation of the FlightRepository is with an in memory collection loaded from a csv file.

PriceCalculator uses all the Calculator objects for each passenger type in chain to calculate the final price, summing the price for each passenger type and rounding the price.

The price is actually calculated with 3 Calculators:
- price for number of passengers (PriceForPassengersCalculator)
- price based on passenger type (PriceBasedOnPassengerTypeCalculator). This calculator uses the AirlineRepository (which works with an in memory collection of airlines loaded from a csv) to retrieve the infant price of the airline.
- price based on departure date (PriceBasedOnDepartureDate)

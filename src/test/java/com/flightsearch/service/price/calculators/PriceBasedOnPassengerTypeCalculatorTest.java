package com.flightsearch.service.price.calculators;

import com.flightsearch.model.Airline;
import com.flightsearch.model.Flight;
import com.flightsearch.model.PassengerType;
import com.flightsearch.repository.AirlineRepository;
import com.flightsearch.service.price.PriceContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PriceBasedOnPassengerTypeCalculatorTest {

    @Mock
    private AirlineRepository airlineRepository;

    private Calculator calculator;

    private String flightCode = "IB9283";
    private Double flightPrice = 1.10;
    private Flight flight = new Flight("MAD", "BCN", flightCode, flightPrice);

    @Before
    public void setUp() {
        reset(airlineRepository);
        calculator = new PriceBasedOnPassengerTypeCalculator(airlineRepository);
    }

    @Test
    public void should_return_the_input_price_for_an_adult() {
        PriceContext context = new PriceContext(flight, PassengerType.ADULT, 1, 1);
        Double accumulatedPrice = 10.1;
        Double resultPrice = calculator.apply(context, accumulatedPrice);
        assertEquals(accumulatedPrice, resultPrice);
    }

    @Test
    public void should_return_a_discount_of_33_percent_for_a_child() {
        PriceContext context = new PriceContext(flight, PassengerType.CHILD, 1, 1);
        Double accumulatedPrice = 10.1;
        Double resultPrice = calculator.apply(context, accumulatedPrice);
        assertTrue(accumulatedPrice * 0.67 == resultPrice);
    }

    @Test
    public void should_return_the_airline_infant_price_for_an_infant() {
        PriceContext context = new PriceContext(flight, PassengerType.INFANT, 1, 1);
        Double accumulatedPrice = 10.1;
        Double infantPrice = 2.32;
        Airline airline = new Airline("IB", "Iberia", infantPrice);
        when(airlineRepository.findAirlineByIataCode(flightCode.substring(0, 2))).thenReturn(Optional.of(airline));
        Double resultPrice = calculator.apply(context, accumulatedPrice);
        assertEquals(infantPrice, resultPrice);
    }

    @Test(expected = RuntimeException.class)
    public void should_for_for_an_infant_if_the_airline_does_not_exists() {
        PriceContext context = new PriceContext(flight, PassengerType.INFANT, 1, 1);
        Double accumulatedPrice = 10.1;
        when(airlineRepository.findAirlineByIataCode(flightCode.substring(0, 2))).thenReturn(Optional.empty());
        calculator.apply(context, accumulatedPrice);
    }

}

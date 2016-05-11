package com.flightsearch.service.price.calculators;

import com.flightsearch.model.Flight;
import com.flightsearch.model.PassengerType;
import com.flightsearch.service.price.PriceContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class PriceBasedOnDepartureDateTest {

    private Calculator calculator;

    private String flightCode = "IB9283";
    private Double flightPrice = 1.10;
    private Flight flight = new Flight("MAD", "BCN", flightCode, flightPrice);

    @Before
    public void setUp() {
        calculator = new PriceBasedOnDepartureDate();
    }

    @Test
    public void should_return_the_input_price_if_the_departure_date_is_between_16_and_30_days() {
        PriceContext context = new PriceContext(flight, PassengerType.ADULT, 16, 1);
        Double accumulatedPrice = 10.1;
        Double resultPrice = calculator.apply(context, accumulatedPrice);
        assertEquals(accumulatedPrice, resultPrice);
    }

    @Test
    public void should_return_eighty_percent_of_the_input_price_if_the_departure_date_is_more_than_30_days() {
        PriceContext context = new PriceContext(flight, PassengerType.CHILD, 31, 1);
        Double accumulatedPrice = 10.1;
        Double resultPrice = calculator.apply(context, accumulatedPrice);
        assertTrue(accumulatedPrice * 0.80 == resultPrice);
    }

    @Test
    public void should_return_input_price_plus_20_percent_if_the_departure_date_is_between_3_and_15_days() {
        PriceContext context = new PriceContext(flight, PassengerType.CHILD, 3, 1);
        Double accumulatedPrice = 10.1;
        Double resultPrice = calculator.apply(context, accumulatedPrice);
        assertTrue(accumulatedPrice * 1.20 == resultPrice);
    }

    @Test
    public void should_return_input_price_plus_50_percent_if_the_departure_date_is_lower_than_3_days() {
        PriceContext context = new PriceContext(flight, PassengerType.CHILD, 2, 1);
        Double accumulatedPrice = 10.1;
        Double resultPrice = calculator.apply(context, accumulatedPrice);
        assertTrue(accumulatedPrice * 1.50 == resultPrice);
    }

}

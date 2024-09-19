package com.gridnine.testing.filter;

import com.gridnine.testing.test.Flight;

import java.util.List;

public class FlightsArrivalBeforeDeparture implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())))
                .toList();
    }
}
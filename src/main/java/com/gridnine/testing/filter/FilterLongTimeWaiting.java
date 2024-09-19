package com.gridnine.testing.filter;

import com.gridnine.testing.test.Flight;
import com.gridnine.testing.test.Segment;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class FilterLongTimeWaiting implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    if (segments.size() < 2) {
                        return true;
                    }

                    long totalGroundTimeInMinutes = 0;
                    for (int i = 1; i < segments.size(); i++) {
                        Duration groundTime = Duration.between(
                                segments.get(i - 1).getArrivalDate(),
                                segments.get(i).getDepartureDate()
                        );
                        totalGroundTimeInMinutes += groundTime.toMinutes();
                    }
                    return totalGroundTimeInMinutes <= 120;
                })
                .collect(Collectors.toList());
    }
}
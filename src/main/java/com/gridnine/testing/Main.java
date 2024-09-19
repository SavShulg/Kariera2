package com.gridnine.testing;

import com.gridnine.testing.filter.FilterBeforeNow;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.FlightsArrivalBeforeDeparture;

import com.gridnine.testing.filter.FilterLongTimeWaiting;
import com.gridnine.testing.test.Flight;
import com.gridnine.testing.test.FlightBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("Все перелеты:");
        flights.forEach(System.out::println);

        FlightFilter beforeNow = new FilterBeforeNow();
        FlightFilter arrivalBeforeDeparture = new FlightsArrivalBeforeDeparture();
        FlightFilter longTimeWaiting = new FilterLongTimeWaiting();

        System.out.println("Вылет до текущего момента времени:");
        beforeNow.filter(flights).forEach(System.out::println);

        System.out.println("Сегменты с датой прилёта раньше даты вылета:");
        arrivalBeforeDeparture.filter(flights).forEach(System.out::println);

        System.out.println("Перелеты, где общее время, проведённое на земле, превышает два часа:");
        longTimeWaiting.filter(flights).forEach(System.out::println);
    }
}
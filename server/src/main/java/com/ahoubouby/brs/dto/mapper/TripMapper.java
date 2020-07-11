package com.ahoubouby.brs.dto.mapper;

import com.ahoubouby.brs.dto.model.TripDto;
import com.ahoubouby.brs.model.Trip;
/*
 * ahoubouby created on 6/30/20
 * E-MAIL: ahoubouby@gmail.com
 */



public class TripMapper {
    public static TripDto toTripDto(Trip trip) {
        return new TripDto()
                .setId(trip.getId())
                .setAgencyCode(trip.getAgency().getCode())
                .setSourceStopCode(trip.getSourceStop().getCode())
                .setSourceStopName(trip.getSourceStop().getName())
                .setDestinationStopCode(trip.getDestStop().getCode())
                .setDestinationStopName(trip.getDestStop().getName())
                .setBusCode(trip.getBus().getCode())
                .setJourneyTime(trip.getJourneyTime())
                .setFare(trip.getFare());
    }
}

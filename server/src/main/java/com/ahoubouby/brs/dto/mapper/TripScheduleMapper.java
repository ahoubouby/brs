package com.ahoubouby.brs.dto.mapper;

import com.ahoubouby.brs.dto.model.TripScheduleDto;
import com.ahoubouby.brs.model.Trip;
import com.ahoubouby.brs.model.TripSchedule;
/*
 * ahoubouby created on 6/30/20
 * E-MAIL: ahoubouby@gmail.com
 */

public class TripScheduleMapper {

    public static TripScheduleDto toTripScheduleDto(TripSchedule tripSchedule) {
        Trip tripDetails = tripSchedule.getTripDetail();
        return new TripScheduleDto()
                .setId(tripSchedule.getId())
                .setTripId(tripDetails.getId())
                .setBusCode(tripDetails.getBus().getCode())
                .setAvailableSeats(tripSchedule.getAvailableSeats())
                .setFare(tripDetails.getFare())
                .setJourneyTime(tripDetails.getJourneyTime())
                .setSourceStop(tripDetails.getSourceStop().getName())
                .setDestinationStop(tripDetails.getDestStop().getName());
    }
}

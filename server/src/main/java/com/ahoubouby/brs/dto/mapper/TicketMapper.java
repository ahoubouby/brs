package com.ahoubouby.brs.dto.mapper;
/*
 * ahoubouby created on 6/30/20
 * E-MAIL: ahoubouby@gmail.com
 */

import com.ahoubouby.brs.dto.model.TicketDto;
import com.ahoubouby.brs.model.Ticket;

public class TicketMapper {
    public static TicketDto toTicketDto(Ticket ticket) {
        return new TicketDto()
                .setId(ticket.getId())
                .setBusCode(ticket.getTripSchedule().getTripDetail().getBus().getCode())
                .setSeatNumber(ticket.getSeatNumber())
                .setSourceStop(ticket.getTripSchedule().getTripDetail().getSourceStop().getName())
                .setDestinationStop(ticket.getTripSchedule().getTripDetail().getDestStop().getName())
                .setCancellable(false)
                .setJourneyDate(ticket.getJourneyDate())
                .setPassengerName(ticket.getPassenger().getFullName())
                .setPassengerMobileNumber(ticket.getPassenger().getMobileNumber());
    }
}

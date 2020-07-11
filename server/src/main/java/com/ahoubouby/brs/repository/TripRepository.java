package com.ahoubouby.brs.repository;

import com.ahoubouby.brs.model.Agency;
import com.ahoubouby.brs.model.Bus;
import com.ahoubouby.brs.model.Stop;
import com.ahoubouby.brs.model.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;

/*
 * ahoubouby created on 6/30/20
 * E-MAIL: ahoubouby@gmail.com
 */



import java.util.List;

public interface TripRepository  extends MongoRepository<Trip, String> {
    Trip findBySourceStopAndDestStopAndBus(Stop source, Stop destination, Bus bus);

    List<Trip> findAllBySourceStopAndDestStop(Stop source, Stop destination);

    List<Trip> findByAgency(Agency agency);
}

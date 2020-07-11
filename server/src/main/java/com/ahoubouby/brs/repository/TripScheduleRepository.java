package com.ahoubouby.brs.repository;

import com.ahoubouby.brs.model.Trip;
import com.ahoubouby.brs.model.TripSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;
/*
 * ahoubouby created on 6/30/20
 * E-MAIL: ahoubouby@gmail.com
 */


public interface TripScheduleRepository extends MongoRepository<TripSchedule, String> {
    TripSchedule findByTripDetailAndTripDate(Trip tripDetail, String tripDate);

}

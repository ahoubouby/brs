package com.ahoubouby.brs.repository;
/*
 * ahoubouby created on 6/30/20
 * E-MAIL: ahoubouby@gmail.com
 */

import com.ahoubouby.brs.model.Agency;
import com.ahoubouby.brs.model.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BusRepository extends MongoRepository<Bus, String> {
    Bus findByCode(String busCode);

    Bus findByCodeAndAgency(String busCode, Agency agency);
}

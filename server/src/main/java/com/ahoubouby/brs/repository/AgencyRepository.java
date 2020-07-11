package com.ahoubouby.brs.repository;
/*
 * ahoubouby created on 6/30/20
 * E-MAIL: ahoubouby@gmail.com
 */

import com.ahoubouby.brs.model.Agency;
import com.ahoubouby.brs.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgencyRepository extends MongoRepository<Agency, String> {
    Agency findByCode(String agencyCode);

    Agency findByOwner(User owner);

    Agency findByName(String name);
}

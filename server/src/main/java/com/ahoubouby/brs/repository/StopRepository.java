package com.ahoubouby.brs.repository;

import com.ahoubouby.brs.model.Stop;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StopRepository extends MongoRepository<Stop, String> {
    Stop findByCode(String code);
}

package com.ahoubouby.brs.repository;

import com.ahoubouby.brs.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);
}

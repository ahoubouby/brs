package com.ahoubouby.brs.repository;

import com.ahoubouby.brs.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, Long> {
}

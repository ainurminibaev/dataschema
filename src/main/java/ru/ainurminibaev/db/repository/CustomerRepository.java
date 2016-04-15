package ru.ainurminibaev.db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.ainurminibaev.db.model.Customer;

/**
 * Created by ainurminibaev on 20.03.16.
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {
}

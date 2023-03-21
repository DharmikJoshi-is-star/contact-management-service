package com.contactmangementservice.repositories;

import com.contactmangementservice.entities.UserContact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContactRepository extends MongoRepository<UserContact, String> {
}

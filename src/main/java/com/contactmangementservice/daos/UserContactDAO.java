package com.contactmangementservice.daos;

import com.contactmangementservice.entities.UserContact;
import com.contactmangementservice.repositories.UserContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Component
public class UserContactDAO {

    private final UserContactRepository userContactRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserContactDAO(UserContactRepository userContactRepository,
                          MongoTemplate mongoTemplate) {
        this.userContactRepository = userContactRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public void saveUserContact(UserContact userContact) {
        userContactRepository.save(userContact);
    }

    public Optional<UserContact> getUserContact(String userContactId) {
        return userContactRepository.findById(userContactId);
    }

    public boolean deleteUserContact(String userContactId) {
        if (this.getUserContact(userContactId).isPresent()) {
            userContactRepository.deleteById(userContactId);
            return true;
        }
        return false;
    }

    public List<UserContact> getContacts() {
        return userContactRepository.findAll();
    }

    public List<UserContact> getUserContactsByFilter(String firstName, String lastName, String email) {
        Criteria criteria = new Criteria();
        if (StringUtils.hasText(firstName)) {
            criteria.and("firstName").regex(firstName);
        }
        if (StringUtils.hasText(lastName)) {
            criteria.and("lastName").regex(lastName);
        }
        if (StringUtils.hasText(email)) {
            criteria.and("email").regex(email);
        }
        Query query = new Query(criteria);
        return mongoTemplate.find(query, UserContact.class);
    }
}

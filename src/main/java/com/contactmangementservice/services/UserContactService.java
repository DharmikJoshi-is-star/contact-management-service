package com.contactmangementservice.services;

import com.contactmangementservice.daos.UserContactDAO;
import com.contactmangementservice.entities.UserContact;
import com.contactmangementservice.exceptions.CMSException;
import com.contactmangementservice.pojo.AddUserContactRequest;
import com.contactmangementservice.pojo.UpdateUserContactRequest;
import com.contactmangementservice.pojo.UserContactResponse;
import com.contactmangementservice.utils.PojoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserContactService {

    private final UserContactDAO userContactDAO;

    @Autowired
    public UserContactService(UserContactDAO userContactDAO) {
        this.userContactDAO = userContactDAO;
    }

    public void addContact(AddUserContactRequest addUserContactRequest) {
        UserContact userContact =
                PojoConverter.convertAddContactRequestToContact(addUserContactRequest);
        userContactDAO.saveUserContact(userContact);
    }

    public void updateContact(String userContactId, UpdateUserContactRequest updateUserContactRequest) {
        Optional<UserContact> userContactOptional = userContactDAO.getUserContact(userContactId);
        if (userContactOptional.isPresent()) {
            UserContact userContact = userContactOptional.get();
            boolean isUpdated = userContact.updateDetails(updateUserContactRequest);
            if (isUpdated) {
                userContactDAO.saveUserContact(userContact);
                return;
            }
        }
        throw new CMSException("User Contact id: " + userContactId + " does not exist!");
    }

    public UserContactResponse getUserContactById(String userContactId) {
        Optional<UserContact> userContactOptional = userContactDAO.getUserContact(userContactId);
        if (userContactOptional.isPresent()) {
            return PojoConverter.convertUserContactToUserContactResponse(userContactOptional.get());
        }
        throw new CMSException("User Contact id: " + userContactId + " does not exist!");
    }

    public List<UserContactResponse> getUserContactsByFilter(String firstName, String lastName, String email) {
        List<UserContact> userContacts = userContactDAO.getUserContactsByFilter(firstName, lastName, email);
        return CollectionUtils.isEmpty(userContacts) ? Collections.emptyList() : userContacts.stream()
                .map(PojoConverter::convertUserContactToUserContactResponse)
                .collect(Collectors.toList());
    }

    public void deleteContact(String userContactId) {
        if (!userContactDAO.deleteUserContact(userContactId)) {
            throw new CMSException("User Contact id: " + userContactId + " does not exist!");
        }
    }
}

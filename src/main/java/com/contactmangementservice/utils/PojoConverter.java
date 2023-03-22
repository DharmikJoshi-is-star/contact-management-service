package com.contactmangementservice.utils;

import com.contactmangementservice.entities.UserContact;
import com.contactmangementservice.pojo.AddUserContactRequest;
import com.contactmangementservice.pojo.UserContactResponse;

public class PojoConverter {

    public static UserContact convertAddContactRequestToContact(AddUserContactRequest addUserContactRequest) {
        UserContact userContact = new UserContact();
        userContact.setFirstName(addUserContactRequest.getFirstName());
        userContact.setLastName(addUserContactRequest.getLastName());
        userContact.setEmail(addUserContactRequest.getEmail());
        userContact.setPhoneNumber(addUserContactRequest.getPhoneNumber());
        return userContact;
    }

    public static UserContactResponse convertUserContactToUserContactResponse(UserContact userContact) {
        return UserContactResponse.builder()
                .userContactId(userContact.getId().toString())
                .firstName(userContact.getFirstName())
                .lastName(userContact.getLastName())
                .email(userContact.getEmail())
                .phoneNumber(userContact.getPhoneNumber())
                .build();
    }
}

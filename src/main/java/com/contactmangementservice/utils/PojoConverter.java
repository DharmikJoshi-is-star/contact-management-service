package com.contactmangementservice.utils;

import com.contactmangementservice.entities.UserContact;
import com.contactmangementservice.pojo.AddUserContactRequest;
import com.contactmangementservice.pojo.UserContactResponse;

public class PojoConverter {

    public static UserContact convertAddContactRequestToContact(AddUserContactRequest addUserContactRequest) {
        return UserContact.builder()
                .firstName(addUserContactRequest.getFirstName())
                .lastName(addUserContactRequest.getLastName())
                .email(addUserContactRequest.getEmail())
                .phoneNumber(addUserContactRequest.getPhoneNumber())
                .build();
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

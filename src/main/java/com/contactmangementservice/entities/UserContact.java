package com.contactmangementservice.entities;

import com.contactmangementservice.pojo.UpdateUserContactRequest;
import com.contactmangementservice.utils.Utils;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Document(collection = "user_contacts")
public class UserContact extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public boolean updateDetails(UpdateUserContactRequest updateUserContactRequest) {
        boolean isUpdated = false;
        if (Utils.isNewValue(this.firstName, updateUserContactRequest.getFirstName())) {
            this.firstName = updateUserContactRequest.getFirstName();
            isUpdated = true;
        }
        if (Utils.isNewValue(this.lastName, updateUserContactRequest.getLastName())) {
            this.lastName = updateUserContactRequest.getLastName();
            isUpdated = true;
        }
        if (Utils.isNewValue(this.email, updateUserContactRequest.getEmail())) {
            this.email = updateUserContactRequest.getEmail();
            isUpdated = true;
        }
        if (Utils.isNewValue(this.phoneNumber, updateUserContactRequest.getPhoneNumber())) {
            this.phoneNumber = updateUserContactRequest.getPhoneNumber();
            isUpdated = true;
        }
        return isUpdated;
    }
}

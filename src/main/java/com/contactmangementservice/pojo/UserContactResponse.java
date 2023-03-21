package com.contactmangementservice.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserContactResponse {

    @ApiModelProperty(notes = "User contact id is unique reference id to access the data", example = "6419e093c3c1a552ac4155d2", required = true)
    private final String userContactId;

    @ApiModelProperty(notes = "First name of user", example = "Dharmik", required = true)
    private final String firstName;

    @ApiModelProperty(notes = "Last name of user", example = "Joshi", required = true)
    private final String lastName;

    @ApiModelProperty(notes = "Email Id name of user",
            example = "dharmikj75@gmail.com", required = true)
    private final String email;

    @ApiModelProperty(
            notes = "Phone number of user, only numeric string of length 10 " +
                    "whose first number should be in range of 6-9",
            example = "9012345678", required = true)
    private final String phoneNumber;
}

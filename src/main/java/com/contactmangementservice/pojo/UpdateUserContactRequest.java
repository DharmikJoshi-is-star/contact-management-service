package com.contactmangementservice.pojo;

import com.contactmangementservice.constants.Constant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateUserContactRequest {

    @ApiModelProperty(notes = "First name of user", example = "Dharmik", allowEmptyValue = true)
    private String firstName;

    @ApiModelProperty(notes = "Last name of user", example = "Joshi", allowEmptyValue = true)
    private String lastName;

    @Pattern(regexp = Constant.REGEX_EMAIL, message = "Email is invalid")
    @ApiModelProperty(notes = "Email Id name of user, only accept valid email id",
            example = "dharmikj75@gmail.com", allowEmptyValue = true)
    private String email;

    @Pattern(regexp = Constant.REGEX_MOBILE, message = "Mobile is invalid")
    @Length(min = 10, max = 10, message = "Mobile should be of length 10")
    @ApiModelProperty(
            notes = "Phone number of user, only accept numeric string of length 10 " +
                    "whose first number should be in range of 6-9",
            example = "9012345678", allowEmptyValue = true)
    private String phoneNumber;
}

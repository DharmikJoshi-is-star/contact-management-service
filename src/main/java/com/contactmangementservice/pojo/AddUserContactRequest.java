package com.contactmangementservice.pojo;

import com.contactmangementservice.constants.Constant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddUserContactRequest {

    @NotBlank(message = "firstName should be present")
    @ApiModelProperty(notes = "First name of user, required field can't be blank", example = "Dharmik", required = true)
    private String firstName;

    @NotBlank(message = "lastName should be present")
    @ApiModelProperty(notes = "Last name of user, required field can't be blank", example = "Joshi", required = true)
    private String lastName;

    @Pattern(regexp = Constant.REGEX_EMAIL, message = "Email is invalid")
    @NotBlank(message = "Email should be present")
    @ApiModelProperty(notes = "Email Id name of user, required field can't be blank and only accept valid email id",
            example = "dharmikj75@gmail.com", required = true)
    private String email;

    @Pattern(regexp = Constant.REGEX_MOBILE, message = "Mobile is invalid")
    @NotBlank(message = "Mobile should be present")
    @Length(min = 10, max = 10, message = "Mobile should be of length 10")
    @ApiModelProperty(
            notes = "Phone number of user, required field can't be blank and only accept numeric string of length 10 " +
                    "whose first number should be in range of 6-9",
            example = "9012345678", required = true)
    private String phoneNumber;
}

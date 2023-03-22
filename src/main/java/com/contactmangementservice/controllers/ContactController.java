package com.contactmangementservice.controllers;

import com.contactmangementservice.pojo.AddUserContactRequest;
import com.contactmangementservice.pojo.UpdateUserContactRequest;
import com.contactmangementservice.pojo.UserContactResponse;
import com.contactmangementservice.services.UserContactService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ContactController {

    private final UserContactService userContactService;

    @Autowired
    public ContactController(UserContactService userContactService) {
        this.userContactService = userContactService;
    }

    @GetMapping("/ping")
    public String ping() {
        return "Consumer-Management-Service::Ping";
    }

    @ApiOperation(value = "To create new user contact")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved the Information"),
            @ApiResponse(code = 401, message = "Unauthorised Access")
    })
    @PostMapping("/api/v1/contacts")
    public ResponseEntity<?> addContact(@Valid @RequestBody AddUserContactRequest addUserContactRequest) {
        userContactService.addContact(addUserContactRequest);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "To update existing user contact with new details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the Information"),
            @ApiResponse(code = 400, message = "userContact not found or invalid request data"),
            @ApiResponse(code = 401, message = "Unauthorised Access")
    })
    @PutMapping("/api/v1/contacts/{userContactId}")
    public ResponseEntity<?> updateContact(
            @PathVariable("userContactId") @ApiParam(name = "userContactId", value = "User Contact id")
                    String userContactId,
            @Valid @RequestBody UpdateUserContactRequest updateUserContactRequest) {
        userContactService.updateContact(userContactId, updateUserContactRequest);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "To get list user contacts with/without using filters firstName, lastName, email",
            notes = "This api will perform pattern matching for all firstName, lastName, email values")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully executed the query",
                    response = UserContactResponse.class),
            @ApiResponse(code = 401, message = "Unauthorised Access")
    })
    @GetMapping("/api/v1/contacts")
    public ResponseEntity<?> getContacts(@RequestParam(required = false) String firstName,
                                         @RequestParam(required = false) String lastName,
                                         @RequestParam(required = false) String email) {
        return ResponseEntity.ok(userContactService.getUserContactsByFilter(firstName, lastName, email));
    }

    @ApiOperation(value = "To get user contact by userContactId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully executed the query",
                    response = UserContactResponse.class),
            @ApiResponse(code = 400, message = "userContact not found"),
            @ApiResponse(code = 401, message = "Unauthorised Access")
    })
    @GetMapping("/api/v1/contacts/{userContactId}")
    public ResponseEntity<?> getContact(
            @PathVariable("userContactId") @ApiParam(name = "userContactId", value = "User Contact id")
                    String userContactId) {
        return ResponseEntity.ok(userContactService.getUserContactById(userContactId));
    }

    @ApiOperation(value = "To delete existing contact")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User contact deleted successfully"),
            @ApiResponse(code = 400, message = "userContact not found"),
            @ApiResponse(code = 401, message = "Unauthorised Access")
    })
    @DeleteMapping("/api/v1/contacts/{userContactId}")
    public ResponseEntity<?> deleteContact(
            @PathVariable("userContactId") @ApiParam(name = "userContactId", value = "User Contact id")
                    String userContactId) {
        userContactService.deleteContact(userContactId);
        return ResponseEntity.ok().build();
    }
}

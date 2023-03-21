# Contact-Management-Service

<h2>CRUD API SPECIFICATIONS</h2>

<a href="APIDocument.json"> Click here to view API Specs  </a>

<h3> # To ADD User Contact</h3>
URL:- http://localhost:8080/contact-management-service/api/v1/contacts <br>
RequestBody:-  <br>

```javascript
{ 
    "firstName" : "firstname",
    "lastName" : "lastname",
    "email" : "abc@gmail.com",
    "phoneNumber" : "6789012345" 
}
```

<hr>

<h3> # To GET User Contacts by filter</h3>
URL:- http://localhost:8080/contact-management-service/api/v1/contacts?firstName=first&lastName=last&email=abc <br>
ResponseBody:- 

```javascript
[
    {
        "userContactId": "6419e093c3c1a552ac4155d2",
        "firstName": "firstname",
        "lastName": "lastname",
        "email": "abc@gmail.com",
        "phoneNumber": "6789012345"
    }
]
```

<hr>

<h3> # To GET User Contact by id</h3>
URL:- http://localhost:8080/contact-management-service/api/v1/contacts/6419e093c3c1a552ac4155d2 <br>
ResponseBody:- 

```javascript

{
    "userContactId": "6419e093c3c1a552ac4155d2",
    "firstName": "firstname",
    "lastName": "lastname",
    "email": "abc@gmail.com",
    "phoneNumber": "6789012345"
}

```

<hr>

<h3> # To Update User Contact</h3>
URL:- http://localhost:8080/contact-management-service/api/v1/contacts/6419e093c3c1a552ac4155d2 <br>
RequestBody:- 

```javascript

{
    "firstName" : "firstname",
    "lastName" : "lastname",
    "email" : "abc@gmail.com", 
    "phoneNumber" : "6789012345" 
}

```

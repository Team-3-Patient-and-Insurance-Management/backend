# Sign Up Test

Quick tip: We put things in the query params if it has a @RequestParam in the code. The same goes for the body section if it has a @RequestBody. If it has both, then we have to include both

## Using postman to sign up
1. Open Postman and create a new request.
2. Set the request type to POST (Can be used if we use the @PostMapping which is used for creating something).
3. Enter the URL: http://localhost:8080/signup.
4. In the params section, enter email as the key and put the test email in the value; also enter the password as the key and put the test password in the value (This is because we used @RequestParam on Line 20 in the userdatabasecontroller)
5. The requestparam is what you will see in the url/link. Anything that is not specified as a requrestparam cannot be put in the params section.
6. When you put the key and value in the params, you will see that the url will automatically change to http://localhost:8080/signUp?email=test&password=passwordtest where "email" and "password" are the keys and the things after the "=" being the value
7. In the Body section, select the raw option and choose JSON from the dropdown.
8. The format for what we put in the body section is in key:value format where the key is the variables in the user class.
9. The only things that can be placed in the body with the key:value pair format is what is placed in the User class. If we want to save anything else, we have to add it to the User class. However, we do not have to put every variable if we dont want to as if we dont they will just be saved as the a key with a null value
10. Enter the JSON data for signing up a user (This the data that we have in the user class). For example:
   ```json
   {
    "phoneNumber": "+1234567890"
    "firstName": "Bob"
    "lastName": "Last"
    "address": "123 Main St"
    ...(Any additional key value pair)
   }
11. Anything that is placed in the body will not be visible in the URL. Only the server and the params will be visible in the URL.

## Using postman to get the user
1. Set the request type to GET (Can be used if we use the @GetMapping which is used to get something).
2. Enter the URL: http://localhost:8080/getUser.
3. Since our code only has a @RequestParam then we only need to put something in the param and leave the body empty
4. Since our @RequestParam asks for the userID specifically, we put userID as the key and the userID that is saved in the firebase database as the value
5. For example, in the params section, with this UID: Vw4kaYg3Cjc506OVJAWpIR97HXo2 we do:
   * Key = userID, value = Vw4kaYg3Cjc506OVJAWpIR97HXo2
6. You will then see that after you put this, the url changes automatically to http://localhost:8080/getUser?userID=Vw4kaYg3Cjc506OVJAWpIR97HXo2
7. We can put anything in the body though it will not do anything so its unecessary. Though for good practice, it is best to leave the body empty 


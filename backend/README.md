# Sign Up Test

2 ways to try and sign up

## Way #1 Using cURL (just copy and paste in terminal or shell)
curl -X POST \
-H "Content-Type: application/json" \
-d '{"email":"example@example.com","password":"password123", "displayName":"John Doe", "phoneNumber":"1234567890"}' \
http://localhost:8080/signup


## Way #2 Using postman
1. Open Postman and create a new request.
2. Set the request type to POST.
3. Enter the URL: http://localhost:8080/signup.
4. In the Headers section, make sure you have Content-Type: application/json.
5. In the Body section, select the raw option and choose JSON from the dropdown.
6. Enter the JSON data for signing up a user. For example:
   {
    "email": "example@example.com",
    "password": "password123",
    "displayName": "John Doe",
    "phoneNumber": "1234567890"
  }

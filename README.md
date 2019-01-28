# accounts
Small REST Service for gmail using JPA (H2) with OAuth2 security and Swagger!
## Take a dive
1.  Download the code and compile
2.  Run with `java -jar JARFILE`
3.  Sample hello world page can be seen on http://localhost/
## Check the API with Swagger
API is accessible trough http://localhost/api use default credentials:
  - user: admin
  - pass: root
Remember to get permissions to execute the API by using the credentials in the Authorize button, you can create your own user by adding it to the `data.sql` file, you can use http://localhost//api/generate_password/{password} to encode your password
## If you prefer to use Postman
Get the access token by calling: 
POST: http://localhost/oauth/token?grant_type=password&username={user}&password={pass}
basic auth:
  - user `trusted-client` 
  - pass `secret` 
After this you can use the `access_token` to make the rest calls i.e. http://localhost/api/account/?access_token={the-token}

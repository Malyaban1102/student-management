# Overview
A RESTful API service built using Spring Boot provides endpoints for Registration and Login and other functionalities, using MySQL to persist the data.

# Features
* Authentication and Authorization : Implemented using Bearer token.
* Student management:
  + Student registration with email, password (encrypted using BCrypt), first name, last name and department.
  + Student login using email and password and a bearer token will get generated.
* CRUD Endpoints to View, Register, Edit, Delete Students:

# Getting Started:
## Prerequisites:
* Java 21 or higher
* MySql
* Postman for API Testing

## Configuration:
1. Open the application.yml file located in src/main/resources directory.
2. Configure the MySQL database connection settings:

       datasource:
         url: ${DB_URI}
         username: ${DB_USERNAME}
         password: ${DB_PASSWORD}
3. Configure Jwt secret & Expiration:
   
       jwt:
         secret-key: ${JWT_SECRET}
         expiration: ${JWT_EXPIRATION}
Create a .env file and set the enviornment variables DB_URI, DB_USERNAME, DB_PASSWORD, JWT_SECRET, JWT_EXPIRATION.

Save the changes to the application.yml file.

## Running the application:
Run the application using Gradle:

    ./mvnw spring-boot:run

## For testing in Postman:
1. Student Registration POST http://localhost:8080/auth/register
   
   No authorization is needed for registration.
   
   JSON body:
   
       {
          "firstName":"Malyaban",
          "lastName":"Ganguly",
          "email":"malyaban@example.com",
          "password":"password123",
          "department":"Computer Science"
       }

   Body:

       Student registered successfully

2. Student Login POST http://localhost:8080/auth/login

   No authorization is needed for Login.

   JSON body:

       {
          "email":"malyaban@example.com",
          "password":"password123"
       }

   Body:

   Token
   
        eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYWx5YWJhbkBleGFtcGxlLmNvbSIsImlhdCI6MTc0NTQxMTAxMCwiZXhwIjoxNzQ1NDk3NDEwfQ.X9H8QCe1gX5H1etkDdPGoPSDdEil5S6LOc8GI68-DXw

4. View all Students GET http://localhost:8080/students

   Auth Type:

       Bearer Token

   Provide the token recieved earlier during Login.

5. View Student by Id GET http://localhost:8080/students/{id}

   Auth Type:

       Bearer Token
   
   Provide the token recieved earlier during Login.

6. Update Student PUT http://localhost:8080/students/{id}

   Auth Type:

       Bearer Token

   JSON Body:

       {
          "firstName":"Malyaban updated",
          "lastName":"Ganguly",
          "email":"malyaban@example.com",
          "password":"password123",
          "department":"Computer Science"
       }

   Body:
   
       Student updated successfully

   Provide the token recieved earlier during Login.

7. Delete Student DELETE http://localhost:8080/students/{id}

   Auth type:

       Bearer Token

   Body:

       Student deleted successfully

   Provide the token recieved earlier during Login.

## For Swagger UI:
http://localhost:8080/swagger-ui/index.html


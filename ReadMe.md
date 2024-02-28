# Kruger Employees API Documentation

![](./ERD diagram.png)

This document outlines the usage of the Kruger Employees API endpoints. The API is designed to manage employee records, including creating employees, managing employee details, and user authentication.
Base URL

Create the database for the Kruger Employees API. Replace kruger_employees with your desired database name if you prefer a different one:

    CREATE DATABASE kruger_employees;

Credentials for admin user:
    
    username: admin
    password: password

All URLs referenced in the documentation have the following base:


    http://localhost:8080/api

The base URL will change depending on the environment where the API is deployed.
Authentication

Several endpoints require a valid JWT token provided in the request header:



    Authorization: Bearer YOUR_JWT_TOKEN

Login

    Endpoint: /auth/login
    Method: POST
    Description: Authenticates a user and returns a JWT token.
    Body:

    json

    {
      "username": "alex1",
      "password": "password"
    }

    Responses:
        status: 200 OK:
        {
            "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGVqYW5kcm90IiwiaWF0IjoxNzA5MTM2MDQ3LCJleHAiOjE3MDkxMzYxMTd9.SD2vPbUUQCCBAoD0qhRz0P4c8sbsec_mpCpvtvP-CCjQYjCqmZSud-vghkLxceZ31f0OJcNJoaevXDoJ2-_M3w",
            "tokenType": "Bearer ",
            "userId": 13,
            "employeeDetailId": 6,
            "role": "EMPLOYEE"
        }


Employee Management

## Create Employee

    Endpoint: /employee
    Method: POST
    Description: Creates a new employee record.
    Header: Authorization: Bearer YOUR_JWT_TOKEN
    Body:

    json

    {
      "names": "Alejandro Andrés",
      "lastNames": "Toledo Freire",
      "email": "atoledofr@gmail.com",
      "cedula": "1716191927"
    }

    Responses:
        status: 200 OK
        {
            "id": 10,
            "names": "alejandro",
            "lastNames": "toledo",
            "email": "atoledofr@gmail.com",
            "cedula": "3377449927",
            "employeeDetail": {
                "id": 10,
                "fechaNacimiento": null,
                "direccionDomicilio": null,
                "telefonoMovil": null,
                "vacunado": false,
                "tipoVacuna": null,
                "fechaVacunacion": null,
                "numeroDosis": null
            },
            "username": "alejandrot3",
            "password": "6WsouSydro"
        }

### Update Employee Detail

    Endpoint: /employee-detail/update
    Method: PUT
    Description: Updates details for an existing employee.
    Body:

    json

    {
        "userId": 13,
        "id": 6,
        "fechaNacimiento": "1990-01-01",
        "direccionDomicilio": "123 Main St",
        "telefonoMovil": "555-1234",
        "vacunado": true,
        "tipoVacuna": "Pfizer",
        "fechaVacunacion": "2021-04-15",
        "numeroDosis": 2
    }

Responses:

    status: 200 OK:
    {
        "id": 6,
        "fechaNacimiento": "2000-02-09",
        "direccionDomicilio": "Quito",
        "telefonoMovil": "0992601505",
        "vacunado": true,
        "tipoVacuna": "Pfizer",
        "fechaVacunacion": "2024-02-02",
        "numeroDosis": 2
    }
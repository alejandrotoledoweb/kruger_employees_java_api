package com.kruger.kruger_database.exceptions;

public class EmployeeAlreadyExistException extends RuntimeException {

    public EmployeeAlreadyExistException(String message) {
        super(message);
    }
}

package com.kruger.kruger_database.dto;

import lombok.Data;

@Data
public class EmployeeResponseDTO {

    private String names;
    private String lastNames;
    private String email;
    private String username;
    private String password;
    private String cedula;

    public EmployeeResponseDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

}

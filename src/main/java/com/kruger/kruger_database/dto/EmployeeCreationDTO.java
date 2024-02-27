package com.kruger.kruger_database.dto;

import com.kruger.kruger_database.models.EmployeeDetail;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EmployeeCreationDTO {

    private Long id;
    @NotNull
    @NotBlank(message = "Names can't be blank")
    private String names;
    @NotNull @NotBlank(message = "Lastnames can't be blank")
    private String lastNames;
    @NotNull @NotBlank(message = "Email can't be blank")
    private String email;
    @NotNull @NotBlank(message = "Cedula can't be blank")
    private String cedula;
    @NotNull @NotBlank(message ="Should have a details employee")
    private EmployeeDetail employeeDetail;
    private String username;
    private String password;
}

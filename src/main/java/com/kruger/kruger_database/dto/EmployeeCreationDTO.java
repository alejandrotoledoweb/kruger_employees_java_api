package com.kruger.kruger_database.dto;

import com.kruger.kruger_database.models.EmployeeDetail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeCreationDTO {

    private Long id;
    @NotNull @NotBlank(message = "Names can't be blank")
    private String names;
    @NotNull @NotBlank(message = "Lastnames can't be blank")
    private String lastNames;
    @NotNull @NotBlank(message = "Email can't be blank")
    private String email;
    @NotNull @NotBlank(message = "Cedula can't be blank")
    private String cedula;
    @NotNull @NotBlank(message ="Should have a details employee")
    private EmployeeDetail employeeDetail;
}

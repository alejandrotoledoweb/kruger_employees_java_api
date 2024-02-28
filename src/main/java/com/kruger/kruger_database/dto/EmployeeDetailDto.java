package com.kruger.kruger_database.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDetailDto {

    private Long id;
    private LocalDate fechaNacimiento;
    private String direccionDomicilio;
    private String telefonoMovil;
    private boolean vacunado;
    private String tipoVacuna;
    private LocalDate fechaVacunacion;
    private Integer numeroDosis;
}

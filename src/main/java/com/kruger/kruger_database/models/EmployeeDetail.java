package com.kruger.kruger_database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employeeDetails")
public class EmployeeDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaNacimiento;
    private String direccionDomicilio;
    private String telefonoMovil;
    private boolean vacunado;
    private String tipoVacuna;
    private LocalDate fechaVacunacion;
    private Integer numeroDosis;

    @OneToOne(mappedBy = "employeeDetail")
    @JsonBackReference
    private Employee employee;
}

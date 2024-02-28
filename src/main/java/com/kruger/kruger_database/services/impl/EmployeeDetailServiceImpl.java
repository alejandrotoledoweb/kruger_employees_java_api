package com.kruger.kruger_database.services.impl;

import com.kruger.kruger_database.dto.EmployeeCreationDTO;
import com.kruger.kruger_database.dto.EmployeeDetailDto;
import com.kruger.kruger_database.dto.GetEmployeeDetailDto;
import com.kruger.kruger_database.models.Employee;
import com.kruger.kruger_database.models.EmployeeDetail;
import com.kruger.kruger_database.models.UserEntity;
import com.kruger.kruger_database.repositories.EmployeeDetailRepository;
import com.kruger.kruger_database.repositories.UserRepository;
import com.kruger.kruger_database.services.EmployeeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class EmployeeDetailServiceImpl implements EmployeeDetailService {

    private EmployeeDetailRepository employeeDetailRepository;
    private UserRepository userRepository;

    @Autowired
    public EmployeeDetailServiceImpl(EmployeeDetailRepository employeeDetailRepository, UserRepository userRepository) {
        this.employeeDetailRepository = employeeDetailRepository;
        this.userRepository = userRepository;
    }


    @Override
    public EmployeeDetailDto updateEmployeeDetail(EmployeeDetailDto employeeDetailDto) {
        // Assuming employeeDetailDto contains an identifier to find the EmployeeDetail
        // This example uses employeeDetailId for simplicity. Adjust as necessary.
        Long employeeDetailId = employeeDetailDto.getId(); // Ensure your DTO has a getId() method or similar.

        // Find the existing EmployeeDetail
        EmployeeDetail existingDetail = employeeDetailRepository.findById(employeeDetailId)
                .orElseThrow(() -> new EntityNotFoundException("EmployeeDetail not found for id: " + employeeDetailId));


        existingDetail.setFechaNacimiento(employeeDetailDto.getFechaNacimiento());
        existingDetail.setDireccionDomicilio(employeeDetailDto.getDireccionDomicilio());
        existingDetail.setTelefonoMovil(employeeDetailDto.getTelefonoMovil());
        existingDetail.setVacunado(employeeDetailDto.isVacunado());
        existingDetail.setTipoVacuna(employeeDetailDto.getTipoVacuna());
        existingDetail.setFechaVacunacion(employeeDetailDto.getFechaVacunacion());
        existingDetail.setNumeroDosis(employeeDetailDto.getNumeroDosis());

        EmployeeDetail updatedDetail = employeeDetailRepository.save(existingDetail);

        return mapToDto(updatedDetail);
    }

    @Override
    public EmployeeDetailDto getEmployeeDetail(Long userId) {
        UserEntity user =
                userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found for id: " + userId));

        EmployeeDetail employeeDetail = user.getEmployee().getEmployeeDetail();

        return mapToDto(employeeDetail);

    }


    private EmployeeDetailDto mapToDto(EmployeeDetail employeeDetail) {
        EmployeeDetailDto employeeDetailDto = new EmployeeDetailDto();

        employeeDetailDto.setId(employeeDetail.getId());
        employeeDetailDto.setFechaNacimiento(employeeDetail.getFechaNacimiento());
        employeeDetailDto.setDireccionDomicilio(employeeDetail.getDireccionDomicilio());
        employeeDetailDto.setTelefonoMovil(employeeDetail.getTelefonoMovil());
        employeeDetailDto.setVacunado(employeeDetail.isVacunado());
        employeeDetailDto.setTipoVacuna(employeeDetail.getTipoVacuna());
        employeeDetailDto.setFechaVacunacion(employeeDetail.getFechaVacunacion());
        employeeDetailDto.setNumeroDosis(employeeDetail.getNumeroDosis());


        return employeeDetailDto;
    }

    private EmployeeDetail mapToEntity(EmployeeDetailDto employeeDetailDto) {
        EmployeeDetail employeeDetail = new EmployeeDetail();

        employeeDetail.setFechaNacimiento(employeeDetailDto.getFechaNacimiento());
        employeeDetail.setDireccionDomicilio(employeeDetailDto.getDireccionDomicilio());
        employeeDetail.setTelefonoMovil(employeeDetailDto.getTelefonoMovil());
        employeeDetail.setVacunado(employeeDetailDto.isVacunado());
        employeeDetail.setTipoVacuna(employeeDetailDto.getTipoVacuna());
        employeeDetail.setFechaVacunacion(employeeDetailDto.getFechaVacunacion());
        employeeDetail.setNumeroDosis(employeeDetailDto.getNumeroDosis());

        return employeeDetail;
    }
}
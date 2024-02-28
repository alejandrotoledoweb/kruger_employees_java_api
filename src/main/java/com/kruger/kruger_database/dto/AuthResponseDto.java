package com.kruger.kruger_database.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String accessToken;
    private String tokenType = "Bearer ";
    private Long userId;
    private Long employeeDetailId;
    private String role;

    public AuthResponseDto(String accessToken, Long userId, Long employeeDetailId,
                           String role) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.employeeDetailId = employeeDetailId;
        this.role = role;
    }
}

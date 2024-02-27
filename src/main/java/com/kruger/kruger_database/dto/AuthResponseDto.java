package com.kruger.kruger_database.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String accessToken;
    private String tokenType = "Bearer ";
    private Long userId;

    public AuthResponseDto(String accessToken, Long userId) {
        this.accessToken = accessToken;
        this.userId = userId;
    }
}

package com.supermercado.supermercado.dtos;

import lombok.Data;

@Data
public class RefreshTokenResponseDTO {
    private String message;
    private String jwt;
}

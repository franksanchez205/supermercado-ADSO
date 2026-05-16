package com.supermercado.supermercado.dtos;

import lombok.Data;

/**
 * DTO que representa la respuesta del inicio de sesión
 */
@Data
public class LoginResponseDTO extends MessageResponseDTO {
    /**
     * Token jwt
     */
    private String jwt;
}

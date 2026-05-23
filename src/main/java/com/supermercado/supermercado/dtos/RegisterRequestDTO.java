package com.supermercado.supermercado.dtos;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String username;
    private String password;
    private String rol;
}

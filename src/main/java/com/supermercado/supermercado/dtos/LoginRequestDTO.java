package com.supermercado.supermercado.dtos;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;

    private String password;
}

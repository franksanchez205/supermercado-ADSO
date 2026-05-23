package com.supermercado.supermercado.dtos;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String username;

    private String password;

    private String rolId;
}
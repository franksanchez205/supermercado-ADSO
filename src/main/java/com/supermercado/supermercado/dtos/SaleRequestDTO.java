package com.supermercado.supermercado.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SaleRequestDTO {
    private Long productId;
    private Integer quantity;
    private BigDecimal unitPrice;
}

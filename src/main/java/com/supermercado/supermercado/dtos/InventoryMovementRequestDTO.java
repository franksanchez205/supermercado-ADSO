package com.supermercado.supermercado.dtos;

import lombok.Data;

@Data
public class InventoryMovementRequestDTO {
    private Long productId;
    private Integer quantity;
    private String type;
    private String note;
}

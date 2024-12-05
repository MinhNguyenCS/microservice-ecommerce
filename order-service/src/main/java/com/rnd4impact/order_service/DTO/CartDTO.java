package com.rnd4impact.order_service.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CartDTO {
    private int cartId;
    private int userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

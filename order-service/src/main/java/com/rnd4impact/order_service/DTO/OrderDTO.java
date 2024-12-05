package com.rnd4impact.order_service.DTO;

import com.rnd4impact.order_service.entity.CartEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {


    private int orderId;
    private int cartId;
    private LocalDateTime orderDate;
    private String orderDesc;
    private int orderFee;
}

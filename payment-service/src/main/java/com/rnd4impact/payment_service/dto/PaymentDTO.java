package com.rnd4impact.payment_service.dto;

import com.rnd4impact.payment_service.entity.OrderEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private int paymentId;
    private int orderId;
    private Boolean isPayed = false;
    private String paymentStatus;
}

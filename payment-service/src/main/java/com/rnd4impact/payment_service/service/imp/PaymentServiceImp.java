package com.rnd4impact.payment_service.service.imp;

import com.rnd4impact.payment_service.dto.PaymentDTO;
import com.rnd4impact.payment_service.entity.PaymentEntity;

import java.util.List;

public interface PaymentServiceImp {
    List<PaymentDTO> getAllPayment();
    PaymentDTO findPaymentById(int id);
    void uploadPayment(int orderId, boolean isPayed, String paymentStatus);
    void updatePayment(int id, int orderId, boolean isPayed, String paymentStatus);
    void deletePayment(int id);
}

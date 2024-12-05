package com.rnd4impact.payment_service.service;

import com.rnd4impact.payment_service.dto.PaymentDTO;
import com.rnd4impact.payment_service.entity.OrderEntity;
import com.rnd4impact.payment_service.entity.PaymentEntity;
import com.rnd4impact.payment_service.repository.OrderRepository;
import com.rnd4impact.payment_service.repository.PaymentRepository;
import com.rnd4impact.payment_service.service.imp.PaymentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService implements PaymentServiceImp {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<PaymentDTO> getAllPayment() {
        List<PaymentEntity> paymentEntities = paymentRepository.findAll();
        List<PaymentDTO> listDTO = new ArrayList<>();
        for (PaymentEntity payment: paymentEntities) {
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setPaymentId(payment.getPaymentId());
            paymentDTO.setOrderId(payment.getOrder().getOrderId());
            paymentDTO.setIsPayed(payment.getIsPayed());
            paymentDTO.setPaymentStatus(payment.getPaymentStatus());
            listDTO.add(paymentDTO);
        }
        return listDTO;
    }

    @Override
    public PaymentDTO findPaymentById(int id) {
        PaymentEntity payment = paymentRepository.findById(id);
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentId(payment.getPaymentId());
        paymentDTO.setOrderId(payment.getOrder().getOrderId());
        paymentDTO.setIsPayed(payment.getIsPayed());
        paymentDTO.setPaymentStatus(payment.getPaymentStatus());
        return paymentDTO;
    }

    @Transactional
    @Override
    public void uploadPayment(int orderId, boolean isPayed, String paymentStatus) {
        PaymentEntity payment = new PaymentEntity();
        OrderEntity order = orderRepository.findById(orderId);
        payment.setOrder(order);
        payment.setIsPayed(isPayed);
        payment.setPaymentStatus(paymentStatus);
        try{
            paymentRepository.save(payment);
        } catch(Exception e) {
            throw new RuntimeException("Insert Payment Error");
        }
    }

    @Override
    public void updatePayment(int id, int orderId, boolean isPayed, String paymentStatus) {
        PaymentEntity payment = paymentRepository.findById(id);
        OrderEntity order = orderRepository.findById(orderId);
        payment.setOrder(order);
        payment.setIsPayed(isPayed);
        payment.setPaymentStatus(paymentStatus);
        try{
            paymentRepository.save(payment);
        } catch(Exception e) {
            throw new RuntimeException("Update Payment Error");
        }
    }

    @Override
    public void deletePayment(int id) {
        PaymentEntity payment = paymentRepository.findById(id);
        try {
            paymentRepository.delete(payment);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting payment with ID:" + id, e);
        }
    }
}

package com.rnd4impact.payment_service.controller;

import com.rnd4impact.payment_service.payload.response.BaseResponse;
import com.rnd4impact.payment_service.service.imp.PaymentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentServiceImp paymentServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllPayment() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(paymentServiceImp.getAllPayment());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable int id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(paymentServiceImp.findPaymentById(id));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadPayment(@RequestParam int orderId,
                                           @RequestParam boolean isPayed,
                                           @RequestParam String paymentStatus) {
        paymentServiceImp.uploadPayment(orderId, isPayed, paymentStatus);
        return new ResponseEntity<>("Upload Payment", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updatePayment(@RequestParam int id,
                                           @RequestParam int orderId,
                                           @RequestParam boolean isPayed,
                                           @RequestParam String paymentStatus) {
        paymentServiceImp.updatePayment(id, orderId, isPayed, paymentStatus);
        return new ResponseEntity<>("Update Payment", HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable int id) {
        paymentServiceImp.deletePayment(id);
        return new ResponseEntity<>("Delete Payment", HttpStatus.OK);
    }
}

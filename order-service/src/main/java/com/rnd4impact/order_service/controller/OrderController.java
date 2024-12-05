package com.rnd4impact.order_service.controller;

import com.rnd4impact.order_service.payload.response.BaseResponse;
import com.rnd4impact.order_service.service.imp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceImp orderServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllOrder() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(orderServiceImp.findAllOrder());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable int id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(orderServiceImp.findOrderById(id));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadOrder(@RequestParam int cartId,
                                         @RequestParam String orderDesc,
                                         @RequestParam int orderFee) {
        orderServiceImp.uploadOrder(cartId, orderDesc, orderFee);
        return new ResponseEntity<>("Upload order Sucessfully", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateOrder(@RequestParam int id,
                                         @RequestParam int cartId,
                                         @RequestParam String orderDesc,
                                         @RequestParam int orderFee) {
        orderServiceImp.updateOrder(id, cartId, orderDesc, orderFee);
        return new ResponseEntity<>("Update order Sucessfully", HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable int id) {
        orderServiceImp.deleteOrder(id);
        return new ResponseEntity<>("Delete order Sucessfully", HttpStatus.OK);
    }
}

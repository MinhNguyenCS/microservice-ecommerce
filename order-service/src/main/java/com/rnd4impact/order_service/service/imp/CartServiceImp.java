package com.rnd4impact.order_service.service.imp;

import com.rnd4impact.order_service.DTO.CartDTO;

import java.util.List;

public interface CartServiceImp {

    List<CartDTO> getAllCart();
    CartDTO getCartById(int id);
    void uploadCart(int userId);
    void updateCart(int cartId, int userId);
    void deleteCart(int id);
}

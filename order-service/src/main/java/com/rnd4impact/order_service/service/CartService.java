package com.rnd4impact.order_service.service;

import com.rnd4impact.order_service.DTO.CartDTO;
import com.rnd4impact.order_service.entity.CartEntity;
import com.rnd4impact.order_service.entity.UserEntity;
import com.rnd4impact.order_service.repository.CartRepository;
import com.rnd4impact.order_service.repository.UserRepository;
import com.rnd4impact.order_service.service.imp.CartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements CartServiceImp {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<CartDTO> getAllCart() {
        List<CartEntity> cartList = cartRepository.findAll();
       List<CartDTO> DTOlist = new ArrayList<>();
       for (CartEntity cart: cartList) {
           CartDTO cartDTO = new CartDTO();
           cartDTO.setCartId(cart.getCartId());
           cartDTO.setUserId(cart.getUser().getUserId());
           DTOlist.add(cartDTO);
       }
        return DTOlist;
    }

    @Override
    public CartDTO getCartById(int id) {
        CartEntity cart = cartRepository.findById(id);
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getCartId());
        cartDTO.setUserId(cart.getUser().getUserId());
        return cartDTO;
    }

    @Override
    public void uploadCart(int userId) {
        CartEntity cart = new CartEntity();
        UserEntity user = userRepository.findById(userId);
        cart.setUser(user);
        try {
            cartRepository.save(cart);
        } catch (Exception e) {
            throw new RuntimeException("Upload cart error", e);
        }
    }

    @Override
    public void updateCart(int cartId, int userId) {

        CartEntity cart = cartRepository.findById(cartId);
        UserEntity user = userRepository.findById(userId);
        cart.setUser(user);
        try {
            cartRepository.save(cart);
        } catch (Exception e) {
            throw new RuntimeException("Update cart error", e);
        }
    }

    @Override
    public void deleteCart(int id) {
        CartEntity cart = cartRepository.findById(id);
        try {
            cartRepository.delete(cart);
        } catch (Exception e) {
            throw new RuntimeException("Delete category error" + e);
        }

    }
}

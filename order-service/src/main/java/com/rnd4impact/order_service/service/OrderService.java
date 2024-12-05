package com.rnd4impact.order_service.service;

import com.rnd4impact.order_service.DTO.OrderDTO;
import com.rnd4impact.order_service.entity.CartEntity;
import com.rnd4impact.order_service.entity.OrderEntity;
import com.rnd4impact.order_service.repository.CartRepository;
import com.rnd4impact.order_service.repository.OrderRepository;
import com.rnd4impact.order_service.service.imp.OrderServiceImp;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements OrderServiceImp {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public List<OrderDTO> findAllOrder() {
        List<OrderEntity> list = orderRepository.findAll();
        List<OrderDTO> dtoList = new ArrayList<>();
        for (OrderEntity item: list) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderId(item.getOrderId());
            orderDTO.setCartId(item.getCart().getCartId());
            orderDTO.setOrderDesc(item.getOrderDesc());
            orderDTO.setOrderFee(item.getOrderFee());
            dtoList.add(orderDTO);
        }
        return dtoList;
    }

    @Override
    public OrderDTO findOrderById(int id) {
        OrderEntity order = orderRepository.findByOrderId(id);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setCartId(order.getCart().getCartId());
        orderDTO.setOrderDesc(order.getOrderDesc());
        orderDTO.setOrderFee(order.getOrderFee());
        return orderDTO;
    }

    @Override
    public void uploadOrder(int cartId, String orderDesc, int orderFee) {
        CartEntity cart = cartRepository.findById(cartId);
        OrderEntity order = new OrderEntity();
        order.setCart(cart);
        order.setOrderDesc(orderDesc);
        order.setOrderFee(orderFee);
        try{
            orderRepository.save(order);
        } catch(Exception e) {
            throw new RuntimeException("Upload order Error");
        }
    }


    @Override
    public void updateOrder(int id, int cartId, String orderDesc, int orderFee) {
        OrderEntity order = orderRepository.findByOrderId(id);
        CartEntity cart = cartRepository.findById(cartId);
        order.setCart(cart);
        order.setOrderDesc(orderDesc);
        order.setOrderFee(orderFee);
        try{
            orderRepository.save(order);
        } catch(Exception e) {
            throw new RuntimeException("Update order Error");
        }
    }

    @Override
    public void deleteOrder(int id) {
        OrderEntity order = orderRepository.findByOrderId(id);
        try{
            orderRepository.delete(order);
        } catch(Exception e) {
            throw new RuntimeException("Delete order Error");
        }
    }
}

package com.rnd4impact.order_service.service.imp;

import com.rnd4impact.order_service.DTO.OrderDTO;
import java.util.List;
public interface OrderServiceImp {
    List<OrderDTO> findAllOrder();
    OrderDTO findOrderById(int id);
    void uploadOrder(int cartId, String orderDesc, int orderFee);
    void updateOrder(int id, int cartId, String orderDesc, int orderFee);
    void deleteOrder(int id);
}

package com.example.Lab7.service;

import com.example.Lab7.model.Order;
import com.example.Lab7.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> getOrders() {
        return orderRepository.getAllOrders();
    }


}

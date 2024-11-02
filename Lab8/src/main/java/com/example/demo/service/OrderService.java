package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order findOrderById(Long id) {
        return orderRepository.findOrderById(id).orElse(null);
    }

    @Transactional
    public List<Order> findByClientId(Long clientId) {
        return orderRepository.findByClientId(clientId);
    }

    @Transactional
    public List<Order> findByDriverId(Long driverId) {
        return orderRepository.findByDriverId(driverId);
    }

    @Transactional
    public Order updateOrder(Long id, Order order) {
        return orderRepository.updateOrder(id, order).orElse(null);
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        orderRepository.cancelOrder(orderId);
    }

    @Transactional
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order addOrder(Order order) {
        return orderRepository.addOrder(order);
    }
}

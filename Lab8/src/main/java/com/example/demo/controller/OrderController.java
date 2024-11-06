package com.example.demo.controller;

import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.OrderDTO;
import com.example.demo.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        logger.info("Fetching all orders");
        List<OrderDTO> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        logger.info("Fetching order with ID: {}", id);
        OrderDTO orderDTO = orderService.findOrderById(id);
        if (orderDTO != null) {
            return ResponseEntity.ok(orderDTO);
        } else {
            logger.error("Order with ID: {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        logger.info("Updating order with ID: {}", id);
        orderDTO.setId(id.intValue());
        OrderDTO updatedOrder = orderService.updateOrder(id, orderDTO);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        } else {
            logger.error("Order with ID: {} not found for update", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO orderDTO) {
        logger.info("Adding new order");
        OrderDTO savedOrder = orderService.addOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        logger.info("Deleting order with ID: {}", id);
        if (orderService.findOrderById(id) != null) {
            orderService.cancelOrder(id);
            return ResponseEntity.noContent().build();
        } else {
            logger.error("Order with ID: {} not found for deletion", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByClientId(@PathVariable Long clientId) {
        logger.info("Fetching orders for client with ID: {}", clientId);
        List<OrderDTO> orders = orderService.findByClientId(clientId);
        if (orders.isEmpty()) {
            logger.error("No orders found for client with ID: {}", clientId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByDriverId(@PathVariable Long driverId) {
        logger.info("Fetching orders for driver with ID: {}", driverId);
        List<OrderDTO> orders = orderService.findByDriverId(driverId);
        if (orders.isEmpty()) {
            logger.error("No orders found for driver with ID: {}", driverId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(orders);
    }
}

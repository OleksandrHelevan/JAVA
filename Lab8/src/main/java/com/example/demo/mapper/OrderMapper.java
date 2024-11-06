package com.example.demo.mapper;

import com.example.demo.entity.Order;
import com.example.demo.model.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderDTO orderDTO);
    OrderDTO toDto(Order order);
}

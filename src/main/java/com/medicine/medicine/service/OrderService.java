package com.medicine.medicine.service;

import com.medicine.medicine.converter.impl.OrderConverter;
import com.medicine.medicine.dto.OrderDto;
import com.medicine.medicine.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderConverter orderConverter;

    @Autowired
    public OrderService(final OrderRepository orderRepository, final OrderConverter orderConverter) {
        this.orderRepository = orderRepository;
        this.orderConverter = orderConverter;
    }

    public OrderDto getOrder(Long id) {
        return orderConverter.convertToDto(orderRepository.getOne(id));
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public List<OrderDto> getAllBetweenDates(Date from, Date to) {
        return
                orderRepository.findAllByCreationDateBetween(from, to)
                .stream()
                .map(orderConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public List<OrderDto> getAllOrderByCreationDate() {
        return orderRepository.findAllByOrderByCreationDateDesc()
                .stream()
                .map(orderConverter::convertToDto)
                .collect(Collectors.toList());
    }
}

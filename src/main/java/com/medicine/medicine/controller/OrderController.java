package com.medicine.medicine.controller;

import com.medicine.medicine.dto.OrderDto;
import com.medicine.medicine.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/create")
    public OrderDto getAllMedicine(@RequestParam long id) {

        if (id == 0) {
            return new OrderDto();
        } else {
            return orderService.getOrder(id);
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity getAllMedicine() {
        final List<OrderDto> allOrders = orderService.getAllOrders();
        if (allOrders != null) {
            return new ResponseEntity<>(allOrders, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("filter")
    /*public List<OrderDto> getAllBetweenDates(@RequestParam(value = "from") Date from
            , @RequestParam(value = "to") Date to) {*/
    public List<OrderDto> getAllBetweenDates(@RequestParam(value = "from") String fromDate
            , @RequestParam(value = "to") String toDate) {
        Date from = null;
        Date to = null;
        try {
            from = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS").parse(fromDate);
            to = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS").parse(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return orderService.getAllBetweenDates(from, to);
    }

    @GetMapping("orderByDate")
    public List<OrderDto> getAllOrderByCreationDate() {
        return orderService.getAllOrderByCreationDate();
    }
}

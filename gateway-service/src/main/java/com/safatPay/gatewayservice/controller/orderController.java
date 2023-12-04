package com.safatPay.gatewayservice.controller;

import com.safatPay.gatewayservice.payload.request.OrderRequestDto;
import com.safatPay.gatewayservice.payload.response.OrderResponseDto;
import com.safatPay.gatewayservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class orderController {

    private final OrderService orderService;

    public orderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place-order")
    ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto responseDto = orderService.placeOrder(orderRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}

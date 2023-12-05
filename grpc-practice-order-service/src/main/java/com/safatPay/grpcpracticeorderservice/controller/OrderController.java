package com.safatPay.grpcpracticeorderservice.controller;

import com.safatPay.grpcpracticeorderservice.payload.request.PaymentRequestDto;
import com.safatPay.grpcpracticeorderservice.service.OrderServiceClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderServiceClient orderService;

    public OrderController(OrderServiceClient orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/start-payment")
    ResponseEntity<String> startPayment(@RequestBody PaymentRequestDto paymentRequest) {
        orderService.startPayment(paymentRequest);
        return new ResponseEntity<>("api executed successfully. check terminal", HttpStatus.OK);
    }

}

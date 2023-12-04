package com.safatPay.paymentservice.controller;

import com.safatPay.paymentservice.payload.request.OrderRequestDto;
import com.safatPay.paymentservice.payload.response.OrderResponseDto;
import com.safatPay.paymentservice.service.PaymentServiceClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class PaymentController {

    private final PaymentServiceClient paymentServiceClient;

    public PaymentController(PaymentServiceClient paymentServiceClient) {
        this.paymentServiceClient = paymentServiceClient;
    }

    @PostMapping("/place-order")
    ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto responseDto = paymentServiceClient.placeOrder(orderRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}

package com.safatPay.grpcpracticeorderservice.service;

import com.example.orderService.OrderOuterClass.Order;
import com.example.orderService.OrderOuterClass.OrderResponse;
import com.example.orderService.OrderServiceGrpc;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @GrpcClient("grpc-payment-service")
    private OrderServiceGrpc.OrderServiceBlockingStub orderServiceStub;

    public OrderResponse placeOrder(Order order) {
        try {
            return orderServiceStub.placeOrder(order);
        } catch (StatusRuntimeException e) {
            throw new RuntimeException("Error placing order", e);
        }
    }
}

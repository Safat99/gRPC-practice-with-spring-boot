package com.safatPay.paymentservice.service;

import com.example.orderService.OrderOuterClass;
import com.example.orderService.OrderServiceGrpc;
import com.safatPay.paymentservice.payload.request.OrderRequestDto;
import com.safatPay.paymentservice.payload.response.OrderResponseDto;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceClient {

    @GrpcClient("grpc-order-service")
    private OrderServiceGrpc.OrderServiceBlockingStub orderServiceBlockingStub;

    public OrderResponseDto placeOrder(OrderRequestDto orderDto) {
        try {

            OrderOuterClass.Order orderProto = convertToProtobuf(orderDto);

            OrderOuterClass.OrderResponse response = orderServiceBlockingStub.placeOrder(orderProto);

            String status = response.getStatus();
            System.out.println("order status: " + status);

            return convertToResponseDto(response);

        } catch (StatusRuntimeException e) {
            throw new RuntimeException("Error placing order", e);
        }
    }

    private OrderOuterClass.Order convertToProtobuf(OrderRequestDto orderDto) {
        return OrderOuterClass.Order.newBuilder()
                .setOrderId(orderDto.getOrderId())
                .setProductName(orderDto.getProductName())
                .setQuantity(orderDto.getQuantity())
                .build();
    }

    private OrderResponseDto convertToResponseDto(OrderOuterClass.OrderResponse response) {
        return OrderResponseDto.builder()
                .status(response.getStatus())
                .build();
    }

}

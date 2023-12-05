package com.safatPay.gatewayservice.service;

import com.example.orderService.OrderOuterClass.Order;
import com.example.orderService.OrderOuterClass.OrderResponse;
import com.example.orderService.OrderOuterClass.OrderStatusUpdate;
import com.example.orderService.OrderOuterClass.OrderStatusRequest;
import com.example.orderService.OrderServiceGrpc;
import com.safatPay.gatewayservice.payload.request.OrderRequestDto;
import com.safatPay.gatewayservice.payload.request.OrderStatusRequestDto;
import com.safatPay.gatewayservice.payload.response.OrderResponseDto;
import com.safatPay.gatewayservice.payload.response.OrderStatusUpdateDto;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @GrpcClient("grpc-order-service")
    private OrderServiceGrpc.OrderServiceBlockingStub orderServiceBlockingStub;

    public OrderResponseDto placeOrder(OrderRequestDto orderDto) {
        try {

            Order orderProto = convertToProtobuf(orderDto);

            OrderResponse response = orderServiceBlockingStub.placeOrder(orderProto);

            String status = response.getStatus();
            System.out.println("order status: " + status);

            return convertToResponseDto(response);

        } catch (StatusRuntimeException e) {
            throw new RuntimeException("Error placing order", e);
        }
    }

    private Order convertToProtobuf(OrderRequestDto orderDto) {
        return Order.newBuilder()
                .setOrderId(orderDto.getOrderId())
                .setProductName(orderDto.getProductName())
                .setQuantity(orderDto.getQuantity())
                .build();
    }

    private OrderResponseDto convertToResponseDto(OrderResponse response) {
        return OrderResponseDto.builder()
                .status(response.getStatus())
                .build();
    }

    public List<OrderStatusUpdateDto> streamOrders(OrderStatusRequestDto orderStatusRequestDto) {
        OrderStatusRequest orderStatusRequest = convertToOrderStatusRequest(orderStatusRequestDto);

        List<OrderStatusUpdate> orderStatusUpdates = new ArrayList<>();
        orderServiceBlockingStub.streamOrders(orderStatusRequest).forEachRemaining(orderStatusUpdates::add);

        List<OrderStatusUpdateDto> orderStatusUpdateDtos = new ArrayList<>();
        for (OrderStatusUpdate orderStatusUpdate : orderStatusUpdates) {
            orderStatusUpdateDtos.add(convertToOrderStatusUpdateDto(orderStatusUpdate));
        }

        return orderStatusUpdateDtos;
    }

    private OrderStatusRequest convertToOrderStatusRequest(OrderStatusRequestDto orderStatusRequestDto) {
        List<String> orderIds = orderStatusRequestDto.getOrderIds();
        OrderStatusRequest.Builder requestBuilder = OrderStatusRequest.newBuilder();
        requestBuilder.addAllOrderIds(orderIds);

        return requestBuilder.build();
    }

    private OrderStatusUpdateDto convertToOrderStatusUpdateDto(OrderStatusUpdate orderStatusUpdate) {
        return OrderStatusUpdateDto.builder()
                .orderId(orderStatusUpdate.getOrderId())
                .productName(orderStatusUpdate.getProductName())
                .status(orderStatusUpdate.getStatus())
                .build();
    }

}

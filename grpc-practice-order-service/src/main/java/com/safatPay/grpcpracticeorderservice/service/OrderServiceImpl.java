package com.safatPay.grpcpracticeorderservice.service;

import com.example.orderService.OrderOuterClass.OrderResponse;
import com.example.orderService.OrderOuterClass.Order;
import com.example.orderService.OrderServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {

    @Override
    public void placeOrder(Order request, StreamObserver<OrderResponse> responseObserver) {

        OrderResponse response = OrderResponse.newBuilder()
                .setStatus("created")
                .build();

        // sending the response back to the client
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}

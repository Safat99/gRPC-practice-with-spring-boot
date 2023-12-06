package com.safatPay.grpcpracticeorderservice.service;

import com.example.paymentService.Payment.PaymentRequest;
import com.example.paymentService.Payment.PaymentResponse;
import com.example.paymentService.PaymentServiceGrpc;
import com.safatPay.grpcpracticeorderservice.payload.request.PaymentRequestDto;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceClient {

    @GrpcClient("grpc-payment-service")
    private PaymentServiceGrpc.PaymentServiceBlockingStub paymentServiceBlockingStub;

    public void startPayment(PaymentRequestDto request) {
        try {

            PaymentRequest paymentRequest = convertToProtobuf(request);

            PaymentResponse response = paymentServiceBlockingStub.processPayment(paymentRequest);
            String status = response.getStatus();
            System.out.println("order status: " + status);

        } catch (StatusRuntimeException e) {
            throw new RuntimeException("Error start connecting with Payment Service", e);
        }
    }

    private PaymentRequest convertToProtobuf(PaymentRequestDto paymentRequestDto) {
        return PaymentRequest.newBuilder()
                .setOrderId(paymentRequestDto.getOrderId())
                .setAmount(paymentRequestDto.getAmount())
                .build();
    }
}

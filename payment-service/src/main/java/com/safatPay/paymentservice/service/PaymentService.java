package com.safatPay.paymentservice.service;

import com.example.paymentService.Payment.PaymentRequest;
import com.example.paymentService.Payment.PaymentResponse;
import com.example.paymentService.PaymentServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class PaymentService extends PaymentServiceGrpc.PaymentServiceImplBase {

    @Override
    public void processPayment(PaymentRequest paymentRequest, StreamObserver<PaymentResponse> responseObserver){

        PaymentResponse response = PaymentResponse.newBuilder()
                .setStatus("Payment processed successfully")
                .build();

        // sending the response back to the client
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

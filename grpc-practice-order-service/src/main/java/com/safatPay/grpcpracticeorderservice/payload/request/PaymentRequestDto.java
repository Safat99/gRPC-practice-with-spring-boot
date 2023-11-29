package com.safatPay.grpcpracticeorderservice.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentRequestDto {
    @JsonProperty("order_id")
    private String orderId;

    private Float amount;
}
package com.safatPay.paymentservice.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderRequestDto {
    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("product_name")
    private String productName;

    private int quantity;
}

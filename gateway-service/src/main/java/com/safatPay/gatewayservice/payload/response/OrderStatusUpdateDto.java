package com.safatPay.gatewayservice.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OrderStatusUpdateDto {
    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("product_name")
    private String productName;

    private String status;
}
package com.safatPay.gatewayservice.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusRequestDto {
    @JsonProperty("order_ids")
    private List<String> orderIds;
}

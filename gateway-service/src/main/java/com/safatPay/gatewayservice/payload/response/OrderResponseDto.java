package com.safatPay.gatewayservice.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OrderResponseDto {
    private String status;
}

package com.deliverytech.delivery.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteDTO {

    private Long id;
    private String name;
    private String category;
    private String address;
    private String phone;
    private BigDecimal feeDelivery;
    private BigDecimal feedback;
    private Boolean active;

}
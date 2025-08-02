package com.deliverytech.delivery.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long id;
    private String numberOrder;
    private LocalDateTime dataOrder;
    private String status;
    private BigDecimal priceTotal;
    private String observations;
    private Long clientId;
    private Long restaurantId;
    private String items; // JSON ou string representando os itens do pedido

}
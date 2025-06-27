package com.deliverytech.delivery.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Produto {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String nome;

private String descricao;

private BigDecimal preco;

private String categoria;

private Boolean disponivel;

@Column(name = "restaurante_id")
private Long restauranteId;

}

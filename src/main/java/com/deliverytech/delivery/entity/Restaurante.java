package com.deliverytech.delivery.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurantes")
public class Restaurante {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String nome;

 private String categoria;

 private String telefone;

 private String endereco;

 @Column(name = "taxa_entrega")
 private BigDecimal taxaEntrega;

 private BigDecimal avaliacao;

 private BigDecimal ativo;

}

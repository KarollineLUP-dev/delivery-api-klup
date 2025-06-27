package com.deliverytech.delivery.entity;

import java.time.LocalDateTime;

import jakarta.annotation.sql.DataSourceDefinition;
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
@Table(name = "clientes")
public class Cliente {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String nome;

 private String email;

 private String telefone;

 private String endereco;

 @Column(name = "data_cadastro")
 private LocalDateTime dataCadastro;

 private Boolean ativo;

}

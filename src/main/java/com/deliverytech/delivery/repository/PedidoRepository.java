package com.deliverytech.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverytech.delivery.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository <Pedido, Long> {

    List<Pedido> findByClientIdOrderByDataOrderDesc(Long clienteId);

    Pedido findByNumberOrder(String numeroPedido);

    List<Pedido> findByRestaurantIdOrderByDataOrderDesc(Long restauranteId);

}
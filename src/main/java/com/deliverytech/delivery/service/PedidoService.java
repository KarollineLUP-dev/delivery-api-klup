package com.deliverytech.delivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deliverytech.delivery.dto.PedidoDTO;
import com.deliverytech.delivery.entity.Cliente;
import com.deliverytech.delivery.entity.Pedido;
import com.deliverytech.delivery.entity.Restaurante;
import com.deliverytech.delivery.enums.StatusPedido;
import com.deliverytech.delivery.repository.ClienteRepository;
import com.deliverytech.delivery.repository.PedidoRepository;
import com.deliverytech.delivery.repository.RestauranteRepository;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    public Pedido create(PedidoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClientId())
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + dto.getClientId()));

        Restaurante restaurante = restauranteRepository.findById(dto.getRestaurantId())
            .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado: " + dto.getRestaurantId()));

        if (!cliente.getActive()) {
            throw new IllegalArgumentException("Cliente inativo não pode fazer pedidos");
        }

        if (!restaurante.getActive()) {
            throw new IllegalArgumentException("Restaurante não está disponível");
        }

        Pedido pedido = new Pedido();
        pedido.setClientId(cliente.getId());
        pedido.setRestaurant(restaurante);
        pedido.setStatus(StatusPedido.PENDENTE.name());
        pedido.setDataOrder(dto.getDataOrder());
        pedido.setNumberOrder(dto.getNumberOrder());
        pedido.setPriceTotal(dto.getPriceTotal());
        pedido.setObservations(dto.getObservations());
        pedido.setItems(dto.getItems());

        return pedidoRepository.save(pedido);
    }

    @Transactional(readOnly = true)
    public List<Pedido> searchByClientId(Long clientId) {
        return pedidoRepository.findByClientIdOrderByDataOrderDesc(clientId);
    }

    public Pedido updateStatus(Long orderId, StatusPedido status) {
        Pedido pedido = pedidoRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + orderId));

        if (pedido.getStatus().equals(StatusPedido.ENTREGUE.name())) {
            throw new IllegalArgumentException("Pedido já finalizado: " + orderId);
        }

        pedido.setStatus(status.name());
        return pedidoRepository.save(pedido);
    }
    
}
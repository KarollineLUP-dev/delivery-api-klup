package com.deliverytech.delivery.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deliverytech.delivery.dto.RestauranteDTO;
import com.deliverytech.delivery.entity.Restaurante;
import com.deliverytech.delivery.repository.PedidoRepository;
import com.deliverytech.delivery.repository.ProdutoRepository;
import com.deliverytech.delivery.repository.RestauranteRepository;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

  
    public Restaurante create(Restaurante restaurante) {
        if (restauranteRepository.findByName(restaurante.getName()).isPresent()) {
            throw new IllegalArgumentException("Restaurante já cadastrado: " + restaurante.getName());
        }

        validateRestaurantData(restaurante);
        restaurante.setActive(true);

        return restauranteRepository.save(restaurante);
    }

    @Transactional(readOnly = true)
    public Optional<Restaurante> searchById(Long id) {
        return restauranteRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<RestauranteDTO> findById(Long id) {
        Optional<Restaurante> byId = restauranteRepository.findById(id);
        if (byId.isEmpty()) {
            return Optional.empty();
        }
        return byId.map(restaurante -> new RestauranteDTO(
                restaurante.getId(),
                restaurante.getName(),
                restaurante.getCategory(),
                restaurante.getAddress(),
                restaurante.getPhone(),
                restaurante.getFeeDelivery(),
                restaurante.getFeedback(),
                restaurante.getActive()));
    }
 
    @Transactional(readOnly = true)
    public List<RestauranteDTO> searchActive() {
        List<Restaurante> byAtivoTrue = restauranteRepository.findByActiveTrue();
        if (byAtivoTrue.isEmpty()) {
            throw new IllegalArgumentException("Nenhum restaurante ativo encontrado");
        }
        return byAtivoTrue.stream()
            .map(restaurante -> new RestauranteDTO(
                restaurante.getId(),
                restaurante.getName(),
                restaurante.getCategory(),
                restaurante.getAddress(),
                restaurante.getPhone(),
                restaurante.getFeeDelivery(),
                restaurante.getFeedback(),
                restaurante.getActive()))
            .toList();
    }

    @Transactional(readOnly = true)
    public List<RestauranteDTO> searchByCategory(String category) {
        List<Restaurante> byCategoria = restauranteRepository.findByCategory(category);
        if (byCategoria.isEmpty()) {
            throw new IllegalArgumentException("Nenhum restaurante encontrado para a categoria: " + category);
        }

        return byCategoria.stream()
                .map(restaurante -> new RestauranteDTO(
                        restaurante.getId(),
                        restaurante.getName(),
                        restaurante.getCategory(),
                        restaurante.getAddress(),
                        restaurante.getPhone(),
                        restaurante.getFeeDelivery(),
                        restaurante.getFeedback(),
                        restaurante.getActive()))
                .toList();
    }
    
    public Restaurante update(Long id, Restaurante restauranteAtualizado) {
        Restaurante restaurante = searchById(id)
            .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado: " + id));

   
        if (!restaurante.getName().equals(restauranteAtualizado.getName()) &&
            restauranteRepository.findByName(restauranteAtualizado.getName()).isPresent()) {
            throw new IllegalArgumentException("Name já cadastrado: " + restauranteAtualizado.getName());
        }

        restaurante.setName(restauranteAtualizado.getName());
        restaurante.setCategory(restauranteAtualizado.getCategory());
        restaurante.setAddress(restauranteAtualizado.getAddress());
        restaurante.setPhone(restauranteAtualizado.getPhone());
        restaurante.setFeeDelivery(restauranteAtualizado.getFeeDelivery());

        return restauranteRepository.save(restaurante);
    }

    public void inactivate(Long id) {
        Restaurante restaurante = searchById(id)
            .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado: " + id));

        restaurante.setActive(false);
        restauranteRepository.save(restaurante);
    }

    private void validateRestaurantData(Restaurante restaurante) {
        if (restaurante.getName() == null || restaurante.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name é obrigatório");
        }

        if (restaurante.getFeeDelivery() != null &&
            restaurante.getFeeDelivery().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Taxa de entrega não pode ser negativa");
        }
    }

    public void delete(Long id) {
        Restaurante restaurante = searchById(id)
            .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado: " + id));
        restauranteRepository.delete(restaurante);
    }
}
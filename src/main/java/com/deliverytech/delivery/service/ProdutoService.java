package com.deliverytech.delivery.service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deliverytech.delivery.dto.ProdutoDTO;
import com.deliverytech.delivery.entity.Produto;
import com.deliverytech.delivery.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto create(Produto produto) {

        produto.setRestaurantId(produto.getRestaurantId());
        produto.setAvailable(produto.getAvailable());

        return produtoRepository.save(produto);
    }

    public List<ProdutoDTO> searchAll() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoDTO> produtosDTO = new ArrayList<>();

        for (Produto produto : produtos) {
            ProdutoDTO dto = new ProdutoDTO(produto.getId(), produto.getName(), produto.getDescription(),
                    produto.getPrice(), produto.getCategory(), produto.getAvailable());
            produtosDTO.add(dto);
        }

        return produtosDTO;
    }

    public Produto searchById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));
    }

    public List<Produto> searchByRestauranteId(Long restauranteId) {
        return produtoRepository.findByRestaurantId(restauranteId);
    }

    @Transactional
    public Produto update(Long id, Produto produtoUpdated) {
        Produto produtoExisting = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));

        validateProductData(produtoUpdated);

        produtoExisting.setName(produtoUpdated.getName());
        produtoExisting.setDescription(produtoUpdated.getDescription());
        produtoExisting.setPrice(produtoUpdated.getPrice());
        produtoExisting.setCategory(produtoUpdated.getCategory());
        produtoExisting.setAvailable(produtoUpdated.getAvailable());

        return produtoRepository.save(produtoExisting);
    }

    private void validateProductData(Produto produto) {
        if (produto.getName() == null || produto.getName().isEmpty()) {
            throw new IllegalArgumentException("Name do produto é obrigatório");
        }
        if (produto.getDescription() == null || produto.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Descrição do produto é obrigatória");
        }
        if (produto.getPrice() == null || produto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço do produto deve ser maior que zero");
        }
        if (produto.getCategory() == null || produto.getCategory().isEmpty()) {
            throw new IllegalArgumentException("Categoria do produto é obrigatória");
        }
    }

    public Produto inactivate(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));

        if (!produto.getAvailable()) {
            throw new IllegalArgumentException("Produto já está inativo: " + id);
        }

        produto.setAvailable(false);
        return produtoRepository.save(produto);
    }

    @Transactional
    public void delete(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));

        produtoRepository.delete(produto);
    }
}
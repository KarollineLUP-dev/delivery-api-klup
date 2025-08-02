package com.deliverytech.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deliverytech.delivery.entity.Cliente;
import com.deliverytech.delivery.repository.ClienteRepository;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente create(Cliente cliente) {
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado: " + cliente.getEmail());
        }

        validateCustomerData(cliente);

        cliente.setActive(true);

        return clienteRepository.save(cliente);
    }

    @Transactional(readOnly = true)
    public Optional<Cliente> searchById(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Cliente> searchByName(String name) {
        return clienteRepository.findByNameContainingIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    public Optional<Cliente> searchByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Cliente> getActives() {
        return clienteRepository.findByActiveTrue();
    }

    public Cliente update(Long id, Cliente clienteUpdated) {
        Cliente cliente = searchById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + id));

        if (!cliente.getEmail().equals(clienteUpdated.getEmail()) &&
                clienteRepository.existsByEmail(clienteUpdated.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado: " + clienteUpdated.getEmail());
        }

        cliente.setName(clienteUpdated.getName());
        cliente.setEmail(clienteUpdated.getEmail());
        cliente.setPhone(clienteUpdated.getPhone());
        cliente.setAddress(clienteUpdated.getAddress());

        return clienteRepository.save(cliente);
    }

    public void delete(Long id) {
        Cliente cliente = searchById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + id));

        cliente.delete();
        clienteRepository.save(cliente);
    }
    
    private void validateCustomerData(Cliente cliente) {
        if (cliente.getName() == null || cliente.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name é obrigatório");
        }

        if (cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }

        if (cliente.getName().length() < 2) {
            throw new IllegalArgumentException("Name deve ter pelo menos 2 caracteres");
        }
    }

}

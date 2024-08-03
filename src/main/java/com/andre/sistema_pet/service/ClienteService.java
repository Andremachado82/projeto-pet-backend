package com.andre.sistema_pet.service;

import com.andre.sistema_pet.dto.ClienteRequest;
import com.andre.sistema_pet.dto.ClienteResponse;
import com.andre.sistema_pet.entity.ClienteEntity;
import com.andre.sistema_pet.exceptions.ResourceNotFoundException;
import com.andre.sistema_pet.mapper.ClienteMapper;
import com.andre.sistema_pet.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ClienteResponse> findAll() {
        return clienteRepository.findAllWithPets().stream()
                .map(ClienteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ClienteResponse create(ClienteRequest request) {
        ClienteEntity cliente = ClienteMapper.toEntity(request);
        cliente = clienteRepository.save(cliente);

        return ClienteMapper.toResponse(cliente);
    }

    public ClienteResponse getClienteById(Long id) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        return modelMapper.map(cliente, ClienteResponse.class);
    }

    @Transactional
    public ClienteResponse update(Long id, ClienteRequest request) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente not found with id " + id));

        // Usar ModelMapper para atualizar os campos do cliente com os valores do request
        modelMapper.map(request, cliente);

        cliente = clienteRepository.save(cliente);

        return ClienteMapper.toResponse(cliente);
    }
}

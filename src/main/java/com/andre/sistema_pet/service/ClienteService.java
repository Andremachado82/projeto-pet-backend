package com.andre.sistema_pet.service;

import com.andre.sistema_pet.dto.ClienteRequest;
import com.andre.sistema_pet.dto.ClienteResponse;
import com.andre.sistema_pet.entity.ClienteEntity;
import com.andre.sistema_pet.mapper.ClienteMapper;
import com.andre.sistema_pet.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteResponse> findAll() {
        return clienteRepository.findAll().stream()
                .map(ClienteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ClienteResponse create(ClienteRequest request) {
        ClienteEntity cliente = ClienteMapper.toEntity(request);
        cliente = clienteRepository.save(cliente);

        return ClienteMapper.toResponse(cliente);
    }
}

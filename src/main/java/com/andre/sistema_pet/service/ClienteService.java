package com.andre.sistema_pet.service;

import com.andre.sistema_pet.dto.ClienteRequest;
import com.andre.sistema_pet.dto.ClienteResponse;
import com.andre.sistema_pet.entity.ClienteEntity;
import com.andre.sistema_pet.mapper.ClienteMapper;
import com.andre.sistema_pet.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public ClienteResponse create(ClienteRequest request) {
        ClienteEntity cliente = ClienteMapper.toEntity(request);
        cliente = clienteRepository.save(cliente);

        return ClienteMapper.toResponse(cliente);
    }
}

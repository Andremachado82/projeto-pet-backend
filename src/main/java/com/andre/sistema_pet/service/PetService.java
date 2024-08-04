package com.andre.sistema_pet.service;

import com.andre.sistema_pet.dto.PetRequest;
import com.andre.sistema_pet.dto.PetResponse;
import com.andre.sistema_pet.entity.ClienteEntity;
import com.andre.sistema_pet.entity.PetEntity;
import com.andre.sistema_pet.mapper.PetMapper;
import com.andre.sistema_pet.repository.ClienteRepository;
import com.andre.sistema_pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Page<PetResponse> findAll(PageRequest pageRequest) {
        Page<PetEntity> petsPage = petRepository.findAll(pageRequest);
        return petsPage.map(PetMapper::toResponse);
    }

    @Transactional
    public PetResponse create(PetRequest request) {
        ClienteEntity cliente = clienteRepository.findById(request.getIdCliente())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        PetEntity pet = PetMapper.toEntity(request, cliente);

        pet = petRepository.save(pet);

        return PetMapper.toResponse(pet);
    }
}

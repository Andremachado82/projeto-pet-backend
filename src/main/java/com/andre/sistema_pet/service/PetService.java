package com.andre.sistema_pet.service;

import com.andre.sistema_pet.dto.ClienteRequest;
import com.andre.sistema_pet.dto.ClienteResponse;
import com.andre.sistema_pet.dto.PetRequest;
import com.andre.sistema_pet.dto.PetResponse;
import com.andre.sistema_pet.entity.ClienteEntity;
import com.andre.sistema_pet.entity.PetEntity;
import com.andre.sistema_pet.mapper.ClienteMapper;
import com.andre.sistema_pet.mapper.PetMapper;
import com.andre.sistema_pet.repository.ClienteRepository;
import com.andre.sistema_pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<PetResponse> findAll() {
        return petRepository.findAll().stream()
                .map(PetMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public PetResponse create(PetRequest request) {
        // Recupera o ClienteEntity com base no idCliente do request
        ClienteEntity cliente = clienteRepository.findById(request.getIdCliente())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        // Mapeia o PetRequest para PetEntity
        PetEntity pet = PetMapper.toEntity(request, cliente);

        // Salva o PetEntity no repositório
        pet = petRepository.save(pet);

        // Converte o PetEntity salvo para PetResponse e retorna
        return PetMapper.toResponse(pet);
    }
}

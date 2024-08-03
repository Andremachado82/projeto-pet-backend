package com.andre.sistema_pet.service;

import com.andre.sistema_pet.dto.*;
import com.andre.sistema_pet.entity.ClienteEntity;
import com.andre.sistema_pet.entity.EspecieEntity;
import com.andre.sistema_pet.entity.RacaEntity;
import com.andre.sistema_pet.exceptions.ResourceNotFoundException;
import com.andre.sistema_pet.mapper.ClienteMapper;
import com.andre.sistema_pet.repository.ClienteRepository;
import com.andre.sistema_pet.repository.EspecieRepository;
import com.andre.sistema_pet.repository.RacaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacaOrEspecieService {

    @Autowired
    private RacaRepository racaRepository;

    @Autowired
    private EspecieRepository especieRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<RacaResponse> findAllRacas() {
        return racaRepository.findAll().stream()
                .map(raca -> modelMapper.map(raca, RacaResponse.class))
                .collect(Collectors.toList());
    }

    public List<EspecieResponse> findAllEspecies() {
        return especieRepository.findAll().stream()
                .map(especie -> modelMapper.map(especie, EspecieResponse.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public RacaResponse create(RacaRequest request) {
        RacaEntity raca = modelMapper.map(request, RacaEntity.class);
        raca = racaRepository.save(raca);

        return modelMapper.map(raca, RacaResponse.class);
    }

    @Transactional
    public EspecieResponse create(EspecieRequest request) {
        EspecieEntity especie = modelMapper.map(request, EspecieEntity.class);
        especie = especieRepository.save(especie);

        return modelMapper.map(especie, EspecieResponse.class);
    }
}

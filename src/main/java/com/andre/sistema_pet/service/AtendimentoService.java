package com.andre.sistema_pet.service;

import com.andre.sistema_pet.dto.AtendimentoRequest;
import com.andre.sistema_pet.dto.AtendimentoResponse;
import com.andre.sistema_pet.entity.AtendimentoEntity;
import com.andre.sistema_pet.entity.ClienteEntity;
import com.andre.sistema_pet.entity.PetEntity;
import com.andre.sistema_pet.exceptions.ResourceNotFoundException;
import com.andre.sistema_pet.mapper.AtendimentoMapper;
import com.andre.sistema_pet.repository.AtendimentoRepository;
import com.andre.sistema_pet.repository.ClienteRepository;
import com.andre.sistema_pet.repository.PetRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PetRepository petRepository;

    public AtendimentoResponse createAtendimento(AtendimentoRequest request) {
        ClienteEntity cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id " + request.getClienteId()));
        PetEntity pet = petRepository.findById(request.getPetId())
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com id " + request.getPetId()));

        AtendimentoEntity atendimentoEntity = AtendimentoMapper.toEntity(request, cliente, pet);

        atendimentoEntity = atendimentoRepository.save(atendimentoEntity);

        return AtendimentoMapper.toResponse(atendimentoEntity);
    }

    public AtendimentoResponse getAtendimentoById(Long id) {
        AtendimentoEntity atendimentoEntity = atendimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atendimento não encontrado com id " + id));

        return AtendimentoMapper.toResponse(atendimentoEntity);
    }

    public List<AtendimentoResponse> getAllAtendimentos() {
        List<AtendimentoEntity> atendimentoEntities = atendimentoRepository.findAll();
        return atendimentoEntities.stream()
                .map(AtendimentoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<AtendimentoResponse> getAgendamentosPorData(LocalDate data) {
        List<AtendimentoEntity> atendimentos = atendimentoRepository.findByDataAtendimento(data);
        return atendimentos.stream().map(AtendimentoMapper::toResponse).toList();
    }

    public List<AtendimentoResponse> getAgendamentosPorSemana(LocalDate dataInicio, LocalDate dataFim) {
        List<AtendimentoEntity> atendimentos = atendimentoRepository.findByDataAtendimentoBetween(dataInicio, dataFim);
        return atendimentos.stream().map(AtendimentoMapper::toResponse).toList();
    }

    public AtendimentoResponse updateAtendimento(Long id, AtendimentoRequest request) {
            AtendimentoEntity atendimentoEntity = atendimentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Atendimento não encontrado com id " + id));
        BeanUtils.copyProperties(request, atendimentoEntity);

        atendimentoEntity = atendimentoRepository.save(atendimentoEntity);

        return AtendimentoMapper.toResponse(atendimentoEntity);
    }
}

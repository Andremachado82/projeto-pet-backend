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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public AtendimentoResponse updateAtendimento(Long id, AtendimentoRequest request) {
        if (atendimentoRepository.existsById(id)) {
            AtendimentoEntity atendimentoEntity = atendimentoRepository.findById(id).orElse(null);
            if (atendimentoEntity != null) {
                // Obter cliente e pet do banco de dados
                ClienteEntity cliente = clienteRepository.findById(request.getClienteId()).orElse(null);
                PetEntity pet = petRepository.findById(request.getPetId()).orElse(null);

                if (cliente != null && pet != null) {
                    // Atualizar o atendimento com os dados do request
                    AtendimentoEntity updatedAtendimento = AtendimentoMapper.toEntity(request, cliente, pet);
                    updatedAtendimento.setId(id); // Manter o ID do atendimento existente

                    // Salvar o atendimento atualizado
                    AtendimentoEntity savedAtendimento = atendimentoRepository.save(updatedAtendimento);

                    // Retornar a resposta com o atendimento atualizado
                    return AtendimentoMapper.toResponse(savedAtendimento);
                }
            }
        }
        return null;
    }
}

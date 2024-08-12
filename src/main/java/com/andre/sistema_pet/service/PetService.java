package com.andre.sistema_pet.service;

import com.andre.sistema_pet.dto.PetRequest;
import com.andre.sistema_pet.dto.PetResponse;
import com.andre.sistema_pet.entity.ClienteEntity;
import com.andre.sistema_pet.entity.EspecieEntity;
import com.andre.sistema_pet.entity.PetEntity;
import com.andre.sistema_pet.entity.RacaEntity;
import com.andre.sistema_pet.exceptions.ResourceNotFoundException;
import com.andre.sistema_pet.mapper.PetMapper;
import com.andre.sistema_pet.repository.ClienteRepository;
import com.andre.sistema_pet.repository.EspecieRepository;
import com.andre.sistema_pet.repository.PetRepository;
import com.andre.sistema_pet.repository.RacaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EspecieRepository especieRepository;

    @Autowired
    private RacaRepository racaRepository;

    @Autowired
    private ModelMapper modelMapper;


    public Page<PetResponse> findAll(PageRequest pageRequest) {
        Page<PetEntity> petsPage = petRepository.findAll(pageRequest);
        return petsPage.map(PetMapper::toResponse);
    }

    @Transactional
    public PetResponse create(PetRequest request) {
        // Buscar o cliente
        ClienteEntity cliente = clienteRepository.findById(request.getIdCliente())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        // Buscar a espécie
        EspecieEntity especie = especieRepository.findById(request.getIdEspecie())
                .orElseThrow(() -> new IllegalArgumentException("Espécie não encontrada"));

        // Buscar a raça
        RacaEntity raca = request.getIdRaca() != null
                ? racaRepository.findById(request.getIdRaca())
                .orElseThrow(() -> new IllegalArgumentException("Raça não encontrada"))
                : null;

        // Criar e mapear a entidade Pet com todas as entidades associadas
        PetEntity pet = PetMapper.toEntity(request, cliente, especie, raca);

        // Salvar a entidade Pet
        pet = petRepository.save(pet);

        // Mapear e retornar a resposta
        return PetMapper.toResponse(pet);
    }

    @Transactional
    public PetResponse update(Long id, PetRequest request) {
        PetEntity pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com id " + id));

        // Atualizar dados básicos
        BeanUtils.copyProperties(request, pet, getNullPropertyNames(request));

        // Atualizar entidades relacionadas
        EspecieEntity especie = especieRepository.findById(request.getIdEspecie())
                .orElseThrow(() -> new ResourceNotFoundException("Espécie não encontrada com id " + request.getIdEspecie()));
        RacaEntity raca = racaRepository.findById(request.getIdRaca())
                .orElseThrow(() -> new ResourceNotFoundException("Raça não encontrada com id " + request.getIdRaca()));

        pet.setEspecie(especie);
        pet.setRaca(raca);

        // Salvar a entidade atualizada no banco de dados
        pet = petRepository.save(pet);

        // Retornar o Pet atualizado como PetResponse
        return modelMapper.map(pet, PetResponse.class);
    }


    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        List<String> nullPropertyNames = new ArrayList<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            if (src.getPropertyValue(pd.getName()) == null) {
                nullPropertyNames.add(pd.getName());
            }
        }
        return nullPropertyNames.toArray(new String[0]);
    }


    @Transactional
    public void delete(Long id) {
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Pet com ID " + id + " não encontrado.");
        }
    }

}

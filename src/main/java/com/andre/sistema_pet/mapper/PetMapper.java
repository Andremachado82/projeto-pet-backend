package com.andre.sistema_pet.mapper;

import com.andre.sistema_pet.dto.PetRequest;
import com.andre.sistema_pet.dto.PetResponse;
import com.andre.sistema_pet.entity.ClienteEntity;
import com.andre.sistema_pet.entity.PetEntity;

import java.time.format.DateTimeFormatter;

public class PetMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static PetEntity toEntity(PetRequest request, ClienteEntity cliente) {
        PetEntity pet = new PetEntity();
        pet.setNome(request.getNome());
        pet.setEspecie(request.getEspecie());
        pet.setSexo(request.getSexo());
        pet.setRaca(request.getRaca());
        pet.setIdade(request.getIdade());
        pet.setStatusDeSaude(request.getStatusDeSaude());
        pet.setUltimaVisitaVeterinaria(request.getUltimaVisitaVeterinaria());
        pet.setDataNascimento(request.getDataNascimento());
        pet.setObservacoes(request.getObservacoes());
        pet.setCliente(cliente);
        return pet;
    }

    public static PetResponse toResponse(PetEntity entity) {
        PetResponse response = new PetResponse();
        response.setId(entity.getId());
        response.setNome(entity.getNome());
        response.setEspecie(entity.getEspecie());
        response.setSexo(entity.getSexo());
        response.setRaca(entity.getRaca());
        response.setIdade(entity.getIdade());
        response.setStatusDeSaude(entity.getStatusDeSaude());
        response.setObservacoes(entity.getObservacoes());
        if (entity.getUltimaVisitaVeterinaria() != null) {
            response.setUltimaVisitaVeterinaria(entity.getUltimaVisitaVeterinaria().format(formatter));
        }

        if (entity.getDataNascimento() != null) {
            response.setDataNascimento(entity.getDataNascimento().format(formatter));
        }
        response.setNomeCliente(entity.getCliente().getNome());
        response.setIdCliente(entity.getCliente().getId());
        return response;
    }
}

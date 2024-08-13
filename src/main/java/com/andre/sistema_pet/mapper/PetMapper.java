package com.andre.sistema_pet.mapper;

import com.andre.sistema_pet.dto.PetRequest;
import com.andre.sistema_pet.dto.PetResponse;
import com.andre.sistema_pet.entity.ClienteEntity;
import com.andre.sistema_pet.entity.EspecieEntity;
import com.andre.sistema_pet.entity.PetEntity;
import com.andre.sistema_pet.entity.RacaEntity;

import java.time.format.DateTimeFormatter;

public class PetMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static PetEntity toEntity(PetRequest request, ClienteEntity cliente, EspecieEntity especie, RacaEntity raca) {
        PetEntity pet = new PetEntity();
        pet.setNome(request.getNome());
        pet.setEspecie(especie);
        pet.setRaca(raca);
        pet.setSexo(request.getSexo());
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
        response.setEspecie(entity.getEspecie().getNome()); // Use nome ou ID, dependendo da necessidade
        response.setSexo(entity.getSexo());
        response.setRaca(entity.getRaca().getNome()); // Use nome ou ID, dependendo da necessidade
        response.setIdade(entity.getIdade());
        response.setStatusDeSaude(entity.getStatusDeSaude());
        response.setObservacoes(entity.getObservacoes());
        response.setAtivo(entity.getAtivo());
        if (entity.getUltimaVisitaVeterinaria() != null) {
            response.setUltimaVisitaVeterinaria(entity.getUltimaVisitaVeterinaria().format(formatter));
        }
        if (entity.getDataNascimento() != null) {
            response.setDataNascimento(entity.getDataNascimento().format(formatter));
        }
        if (entity.getCliente() != null) {
            response.setNomeCliente(entity.getCliente().getNome());
            response.setIdCliente(entity.getCliente().getId());
        }
        return response;
    }

    public static PetRequest toRequest(PetEntity entity) {
        PetRequest request = new PetRequest();
        request.setNome(entity.getNome());
        request.setIdEspecie(entity.getEspecie().getId()); // Use ID
        request.setSexo(entity.getSexo());
        request.setIdRaca(entity.getRaca().getId()); // Use ID
        request.setIdade(entity.getIdade());
        request.setStatusDeSaude(entity.getStatusDeSaude());
        request.setUltimaVisitaVeterinaria(entity.getUltimaVisitaVeterinaria());
        request.setDataNascimento(entity.getDataNascimento());
        request.setObservacoes(entity.getObservacoes());
        request.setAtivo(entity.getAtivo());
        return request;
    }
}

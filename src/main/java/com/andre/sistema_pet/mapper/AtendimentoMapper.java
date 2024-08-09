package com.andre.sistema_pet.mapper;

import com.andre.sistema_pet.dto.AtendimentoRequest;
import com.andre.sistema_pet.dto.AtendimentoResponse;
import com.andre.sistema_pet.entity.AtendimentoEntity;
import com.andre.sistema_pet.entity.ClienteEntity;
import com.andre.sistema_pet.entity.PetEntity;

public class AtendimentoMapper {

    public static AtendimentoEntity toEntity(AtendimentoRequest request, ClienteEntity cliente, PetEntity pet) {
        AtendimentoEntity atendimento = new AtendimentoEntity();
        atendimento.setDataAtendimento(request.getDataAtendimento());
        atendimento.setHoraAtendimento(request.getHoraAtendimento());
        atendimento.setTipoAtendimento(request.getTipoAtendimento());
        atendimento.setDescricaoAtendimento(request.getDescricaoAtendimento());
        atendimento.setStatus(request.getStatus());
        atendimento.setCliente(cliente);
        atendimento.setPet(pet);
        atendimento.setValorAtendimento(request.getValorAtendimento());
        return atendimento;
    }

    public static AtendimentoResponse toResponse(AtendimentoEntity entity) {
        AtendimentoResponse response = new AtendimentoResponse();
        response.setId(entity.getId());
        response.setDataAtendimento(entity.getDataAtendimento());
        response.setHoraAtendimento(entity.getHoraAtendimento());
        response.setTipoAtendimento(entity.getTipoAtendimento());
        response.setDescricaoAtendimento(entity.getDescricaoAtendimento());
        response.setStatus(entity.getStatus());
        response.setValorAtendimento(entity.getValorAtendimento());

        // Mapear cliente e pet para a resposta
        if (entity.getCliente() != null) {
            response.setCliente(ClienteMapper.toResponse(entity.getCliente()));
        }

        if (entity.getPet() != null) {
            response.setPet(PetMapper.toResponse(entity.getPet()));
        }

        return response;
    }
}

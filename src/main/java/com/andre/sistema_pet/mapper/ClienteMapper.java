package com.andre.sistema_pet.mapper;

import com.andre.sistema_pet.dto.ClienteRequest;
import com.andre.sistema_pet.dto.ClienteResponse;
import com.andre.sistema_pet.entity.ClienteEntity;
import com.andre.sistema_pet.entity.PetEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapper {

    public static ClienteEntity toEntity(ClienteRequest request) {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setNome(request.getNome());
        cliente.setNomeFantasia(request.getNomeFantasia());
        cliente.setCep(request.getCep());
        cliente.setTelefone(request.getTelefone());
        cliente.setEmail(request.getEmail());
        cliente.setCpfCnpj(request.getCpfCnpj());
        cliente.setLogradouro(request.getLogradouro());
        cliente.setNro(request.getNro());
        cliente.setBairro(request.getBairro());
        cliente.setLocalidade(request.getLocalidade());
        cliente.setUf(request.getUf());
        return cliente;
    }

    public static ClienteResponse toResponse(ClienteEntity cliente) {
        ClienteResponse response = new ClienteResponse();
        response.setId(cliente.getId());
        response.setNome(cliente.getNome());
        response.setNomeFantasia(cliente.getNomeFantasia());
        response.setCep(cliente.getCep());
        response.setTelefone(cliente.getTelefone());
        response.setEmail(cliente.getEmail());
        response.setCpfCnpj(cliente.getCpfCnpj());
        response.setLogradouro(cliente.getLogradouro());
        response.setNro(cliente.getNro());
        response.setBairro(cliente.getBairro());
        response.setLocalidade(cliente.getLocalidade());
        response.setUf(cliente.getUf());

        // Adiciona os pets à resposta
        if (cliente.getPets() != null) {
            response.setPets(cliente.getPets().stream()
                    .map(PetMapper::toResponse) // Assumindo que você tem um PetMapper
                    .collect(Collectors.toList()));
        }

        return response;
    }

    public static ClienteRequest toRequest(ClienteEntity cliente) {
        ClienteRequest request = new ClienteRequest();
        request.setNome(cliente.getNome());
        request.setNomeFantasia(cliente.getNomeFantasia());
        request.setCep(cliente.getCep());
        request.setTelefone(cliente.getTelefone());
        request.setEmail(cliente.getEmail());
        request.setCpfCnpj(cliente.getCpfCnpj());
        request.setLogradouro(cliente.getLogradouro());
        request.setNro(cliente.getNro());
        request.setBairro(cliente.getBairro());
        request.setLocalidade(cliente.getLocalidade());
        request.setUf(cliente.getUf());
        return request;
    }
}

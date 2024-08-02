package com.andre.sistema_pet.mapper;

import com.andre.sistema_pet.dto.ClienteRequest;
import com.andre.sistema_pet.dto.ClienteResponse;
import com.andre.sistema_pet.entity.ClienteEntity;

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
        cliente.setCidade(request.getCidade());
        cliente.setEstado(request.getEstado());
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
        response.setCidade(cliente.getCidade());
        response.setEstado(cliente.getEstado());
        return response;
    }
}

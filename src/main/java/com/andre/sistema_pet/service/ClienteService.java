package com.andre.sistema_pet.service;

import com.andre.sistema_pet.dto.ClienteRequest;
import com.andre.sistema_pet.dto.ClienteResponse;
import com.andre.sistema_pet.entity.ClienteEntity;
import com.andre.sistema_pet.exceptions.ResourceNotFoundException;
import com.andre.sistema_pet.mapper.ClienteMapper;
import com.andre.sistema_pet.repository.ClienteRepository;
import com.andre.sistema_pet.repository.PetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<ClienteResponse> findAll(PageRequest pageRequest) {
        Page<ClienteEntity> clientesPage = clienteRepository.findAll(pageRequest);
        clientesPage.forEach(cliente -> cliente.setPets(petRepository.findByClienteId(cliente.getId()))); // Carregar pets manualmente

        return clientesPage.map(ClienteMapper::toResponse);
    }

    @Transactional
    public ClienteResponse create(ClienteRequest request) {
        ClienteEntity cliente = ClienteMapper.toEntity(request);
        cliente = clienteRepository.save(cliente);

        return ClienteMapper.toResponse(cliente);
    }

    public ClienteResponse getClienteById(Long id) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        return modelMapper.map(cliente, ClienteResponse.class);
    }

    @Transactional
    public ClienteResponse update(Long id, ClienteRequest request) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id " + id));

        BeanUtils.copyProperties(request, cliente);

        cliente = clienteRepository.save(cliente);

        return ClienteMapper.toResponse(cliente);
    }

    public List<ClienteResponse> findByName(String nome) {
        List<ClienteEntity> clientes = clienteRepository.findByNomeContainingIgnoreCase(nome);
        return clientes.stream()
                .map(ClienteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Cliente com ID " + id + " não encontrado.");
        }
    }
}

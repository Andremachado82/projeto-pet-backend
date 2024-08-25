package com.andre.sistema_pet.service;

import com.andre.sistema_pet.dto.VendaRequest;
import com.andre.sistema_pet.dto.VendaResponse;
import com.andre.sistema_pet.entity.ClienteEntity;
import com.andre.sistema_pet.entity.ItemVendaEntity;
import com.andre.sistema_pet.entity.VendaEntity;
import com.andre.sistema_pet.mapper.VendaMapper;
import com.andre.sistema_pet.repository.ClienteRepository;
import com.andre.sistema_pet.repository.ItemVendaRepository;
import com.andre.sistema_pet.repository.VendaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private VendaMapper vendaMapper;


    @Transactional
    public VendaResponse criarVenda(VendaRequest request) {
        VendaEntity vendaEntity = vendaMapper.toEntity(request);

        vendaEntity = vendaRepository.save(vendaEntity);

        final VendaEntity savedVendaEntity = vendaEntity;

        savedVendaEntity.getItens().forEach(item -> item.setVenda(savedVendaEntity));

        vendaEntity = vendaRepository.save(savedVendaEntity);

        return vendaMapper.toResponse(vendaEntity);
    }


    @Transactional
    public void finalizarVenda(VendaRequest request) {
        VendaEntity vendaEntity = vendaMapper.toEntity(request);

        if (vendaEntity.getCliente() == null) {
            throw new EntityNotFoundException("Cliente não encontrado");
        }
        vendaEntity = vendaRepository.save(vendaEntity);

        for (ItemVendaEntity item : vendaEntity.getItens()) {
            item.setVenda(vendaEntity);
            itemVendaRepository.save(item);
        }
    }

    @Transactional
    public VendaResponse atualizarVenda(Long id, VendaRequest request) {
        VendaEntity vendaExistente = vendaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venda não encontrada"));

        vendaMapper.updateEntityFromRequest(request, vendaExistente);

        VendaEntity updatedVendaEntity = vendaRepository.save(vendaExistente);

        final VendaEntity finalVendaEntity = updatedVendaEntity;

        finalVendaEntity.getItens().forEach(item -> item.setVenda(finalVendaEntity));

        updatedVendaEntity = vendaRepository.save(finalVendaEntity);

        return vendaMapper.toResponse(updatedVendaEntity);
    }

}

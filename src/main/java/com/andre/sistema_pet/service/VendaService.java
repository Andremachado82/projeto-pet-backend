package com.andre.sistema_pet.service;

import com.andre.sistema_pet.dto.ItemVendaRequest;
import com.andre.sistema_pet.dto.VendaRequest;
import com.andre.sistema_pet.dto.VendaResponse;
import com.andre.sistema_pet.entity.ItemVendaEntity;
import com.andre.sistema_pet.entity.VendaEntity;
import com.andre.sistema_pet.mapper.ItemVendaMapper;
import com.andre.sistema_pet.mapper.VendaMapper;
import com.andre.sistema_pet.repository.ClienteRepository;
import com.andre.sistema_pet.repository.ItemVendaRepository;
import com.andre.sistema_pet.repository.VendaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

        vendaEntity.setIdVenda(null);

        for (ItemVendaEntity item : vendaEntity.getItens()) {
            item.setVenda(vendaEntity);
        }

        VendaEntity savedVendaEntity = vendaRepository.save(vendaEntity);

        return vendaMapper.toResponse(savedVendaEntity);
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

    public Page<VendaResponse> findAll(PageRequest pageRequest) {
        Page<VendaEntity> vendasPage = vendaRepository.findAll(pageRequest);

        return vendasPage.map(vendaEntity -> vendaMapper.toResponse(vendaEntity));
    }

    public VendaResponse getVendaPorId(Long id) {
        VendaEntity venda = vendaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        return vendaMapper.toResponse(venda);
    }

    @Transactional
    public VendaResponse atualizarVenda(Long id, VendaRequest request) {
        VendaEntity vendaExistente = vendaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venda não encontrada"));
        vendaExistente.getItens().clear();

        BeanUtils.copyProperties(request, vendaExistente, "idVenda", "itens");

        if (request.getItens() != null) {
            for (ItemVendaRequest itemRequest : request.getItens()) {
                ItemVendaEntity itemEntity = ItemVendaMapper.toEntity(itemRequest);
                itemEntity.setVenda(vendaExistente);
                vendaExistente.getItens().add(itemEntity);
            }
        }
        vendaExistente = vendaRepository.save(vendaExistente);

        return vendaMapper.toResponse(vendaExistente);
    }
}

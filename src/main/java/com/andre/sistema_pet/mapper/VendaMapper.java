package com.andre.sistema_pet.mapper;

import com.andre.sistema_pet.dto.VendaRequest;
import com.andre.sistema_pet.dto.VendaResponse;
import com.andre.sistema_pet.entity.ClienteEntity;
import com.andre.sistema_pet.entity.ItemVendaEntity;
import com.andre.sistema_pet.entity.VendaEntity;
import com.andre.sistema_pet.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VendaMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    public VendaEntity toEntity(VendaRequest request) {
        VendaEntity vendaEntity = modelMapper.map(request, VendaEntity.class);

        // Mapear o cliente
        if (request.getClienteId() != null) {
            ClienteEntity cliente = clienteRepository.findById(request.getClienteId())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
            vendaEntity.setCliente(cliente);
        }

        // Mapear os itens
        List<ItemVendaEntity> itens = request.getItens().stream()
                .map(itemRequest -> {
                    ItemVendaEntity itemEntity = modelMapper.map(itemRequest, ItemVendaEntity.class);
                    return itemEntity;
                })
                .collect(Collectors.toList());
        vendaEntity.setItens(itens);

        return vendaEntity;
    }

    public void updateEntityFromRequest(VendaRequest request, VendaEntity vendaEntity) {
        // Atualiza os campos da entidade venda com base no request
        modelMapper.map(request, vendaEntity);

        // Atualizar o cliente se necessário
        if (request.getClienteId() != null) {
            ClienteEntity cliente = clienteRepository.findById(request.getClienteId())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
            vendaEntity.setCliente(cliente);
        }

        // Atualizar os itens
        List<ItemVendaEntity> itens = request.getItens().stream()
                .map(itemRequest -> {
                    ItemVendaEntity itemEntity = modelMapper.map(itemRequest, ItemVendaEntity.class);
                    return itemEntity;
                })
                .collect(Collectors.toList());
        vendaEntity.setItens(itens);
    }


    public static VendaResponse toResponse(VendaEntity venda) {
        VendaResponse response = new VendaResponse();
        response.setId(venda.getId());
        response.setDataVenda(venda.getDataVenda());
        response.setStatus(venda.getStatus());
        response.setTotalBruto(venda.getTotalBruto());
        response.setTotalDesconto(venda.getTotalDesconto());
        response.setTotalVenda(venda.getTotalVenda());
        response.setDescontoGeral(venda.getDescontoGeral());
        response.setArredondamento(venda.getArredondamento());
        response.setClienteId(venda.getCliente().getId());
        response.setNomeCliente(venda.getCliente().getNome());
        response.setDataCriacao(venda.getDataCriacao());
        response.setItens(venda.getItens().stream().map(ItemVendaMapper::toResponse).collect(Collectors.toList()));
        return response;
    }
}

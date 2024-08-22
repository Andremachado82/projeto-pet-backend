package com.andre.sistema_pet.mapper;

import com.andre.sistema_pet.dto.VendaRequest;
import com.andre.sistema_pet.dto.VendaResponse;
import com.andre.sistema_pet.entity.ClienteEntity;
import com.andre.sistema_pet.entity.VendaEntity;

import java.util.stream.Collectors;

public class VendaMapper {

    public static VendaEntity toEntity(VendaRequest request, ClienteEntity cliente) {
        VendaEntity venda = new VendaEntity();
        venda.setDataVenda(request.getDataVenda());
        venda.setStatus(request.getStatus());
        venda.setTotalBruto(request.getTotalBruto());
        venda.setTotalDesconto(request.getTotalDesconto());
        venda.setTotalVenda(request.getTotalVenda());
        venda.setDescontoGeral(request.getDescontoGeral());
        venda.setArredondamento(request.getArredondamento());
        venda.setCliente(cliente);
        venda.setItens(request.getItens().stream().map(ItemVendaMapper::toEntity).collect(Collectors.toList()));
        return venda;
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

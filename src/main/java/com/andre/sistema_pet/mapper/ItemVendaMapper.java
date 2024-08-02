package com.andre.sistema_pet.mapper;

import com.andre.sistema_pet.dto.ItemVendaRequest;
import com.andre.sistema_pet.dto.ItemVendaResponse;
import com.andre.sistema_pet.entity.ItemVendaEntity;

public class ItemVendaMapper {

    public static ItemVendaEntity toEntity(ItemVendaRequest request) {
        ItemVendaEntity item = new ItemVendaEntity();

        // Calcular totalSemDesconto e total
        double totalSemDesconto = request.getQuantidade() * request.getPrecoUnitario();
        double descontoPercentual = request.getDesconto() / 100.0;
        double total = totalSemDesconto - (totalSemDesconto * descontoPercentual);

        item.setNome(request.getNome());
        item.setCategoria(request.getCategoria());
        item.setPrecoUnitario(request.getPrecoUnitario());
        item.setDescricao(request.getDescricao());
        item.setQuantidade(request.getQuantidade());
        item.setDesconto(request.getDesconto());
        item.setTotalSemDesconto(totalSemDesconto);
        item.setTotal(total);
        return item;
    }

    public static ItemVendaResponse toResponse(ItemVendaEntity item) {
        ItemVendaResponse response = new ItemVendaResponse();
        response.setId(item.getId());
        response.setNome(item.getNome());
        response.setCategoria(item.getCategoria());
        response.setPrecoUnitario(item.getPrecoUnitario());
        response.setDescricao(item.getDescricao());
        response.setQuantidade(item.getQuantidade());
        response.setDesconto(item.getDesconto());
        response.setTotalSemDesconto(item.getTotalSemDesconto());
        response.setTotal(item.getTotal());
        return response;
    }
}

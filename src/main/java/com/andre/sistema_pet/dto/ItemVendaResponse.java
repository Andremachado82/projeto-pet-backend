package com.andre.sistema_pet.dto;

import lombok.Data;

@Data
public class ItemVendaResponse {

    private Long id;
    private String nome;
    private String categoria;
    private Double precoUnitario;
    private String descricao;
    private Integer quantidade;
    private Double desconto;
    private Double totalSemDesconto;
    private Double total;
}

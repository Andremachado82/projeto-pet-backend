package com.andre.sistema_pet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemVendaRequest {

    @NotNull
    private String nome;

    @NotNull
    private String categoria;

    @NotNull
    private Double precoUnitario;

    @NotNull
    private String descricao;

    @NotNull
    private Integer quantidade;

    @NotNull
    private Double desconto;

    @NotNull
    private Double totalSemDesconto;
}

package com.andre.sistema_pet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class VendaRequest {

    @NotNull
    private Date dataVenda;

    @NotNull
    private String status;

    @NotNull
    private Double totalBruto;

    @NotNull
    private Double totalDesconto;

    @NotNull
    private Double totalVenda;

    @NotNull
    private Double descontoGeral;

    @NotNull
    private Double arredondamento;

    @NotNull
    private Long clienteId;

    @NotNull
    private List<ItemVendaRequest> itens;
}
package com.andre.sistema_pet.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class VendaResponse {

    private Long id;
    private Date dataVenda;
    private String status;
    private Double totalBruto;
    private Double totalDesconto;
    private Double totalVenda;
    private Double descontoGeral;
    private Double arredondamento;
    private ClienteResponse cliente;
    private List<ItemVendaResponse> itens;
}

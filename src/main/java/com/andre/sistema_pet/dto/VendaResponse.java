package com.andre.sistema_pet.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private Long clienteId;
    private String nomeCliente;
    private List<ItemVendaResponse> itens;
    private LocalDateTime dataCriacao;
}

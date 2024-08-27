package com.andre.sistema_pet.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class VendaResponse {

    private Long idVenda;
    private Date dataVenda;
    private String status;
    private Double totalBruto;
    private Double totalDesconto;
    private Double totalVenda;
    private Double descontoGeral;
    private Double arredondamento;
    private ClienteResponse cliente;
    private Long clienteId;
    private String nomeCliente;
    private List<ItemVendaResponse> itens;
    private LocalDateTime dataCriacao;
    private Double valorTotal;
    private String tipoCartao;
    private String formaPagamento;
    private Integer numeroCheque;
    private Integer bancoCheque;
    private Date dataCompensacaoCheque;
}

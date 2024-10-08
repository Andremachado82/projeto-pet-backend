package com.andre.sistema_pet.dto;

import com.andre.sistema_pet.enums.FormaPagamento;
import com.andre.sistema_pet.enums.TipoCartao;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class VendaRequest {

    private Long idVenda;

    @NotNull
    private Date dataVenda;

    @NotEmpty
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

    private TipoCartao tipoCartao;

    private FormaPagamento formaPagamento;

    private Integer numeroCheque;

    private Integer bancoCheque;

    private Date dataCompensacaoCheque;
}
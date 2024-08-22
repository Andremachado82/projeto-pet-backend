package com.andre.sistema_pet.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProdutoRequest {

    @NotEmpty
    private String nome;

    private String categoria;

    private String marca;

    private String unidade;

    private LocalDate dataValidade;

    @NotNull
    private Double precoCusto;

    @NotNull
    private Double precoVenda;

    private Double margemLucro;

    private Float comissao;

    private String ncm;

    private String codigoInterno;

    private String codigoBarras;

    private Long fornecedorId;

    private String nomeFornecedor;

    private Boolean ativo = true;
}

package com.andre.sistema_pet.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProdutoResponse {

    private Long id;

    private String nome;

    private String categoria;

    private String marca;

    private String unidade;

    private LocalDate dataValidade;

    private Double precoCusto;

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

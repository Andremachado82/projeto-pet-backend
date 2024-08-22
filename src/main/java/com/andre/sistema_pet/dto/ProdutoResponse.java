package com.andre.sistema_pet.dto;

import com.andre.sistema_pet.entity.FornecedorEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

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

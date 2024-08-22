package com.andre.sistema_pet.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "tb_produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String categoria;

    private String marca;

    private String unidade;

    private LocalDate dataValidade;

    @Column(name = "preco_custo")
    private Double precoCusto;

    @Column(name = "preco_venda")
    private Double precoVenda;

    @Column(name = "margem_lucro")
    private Double margemLucro;

    private Float comissao;

    private String ncm;

    @Column(name = "codigo_interno")
    private String codigoInterno;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private FornecedorEntity fornecedor;

    @Column(name = "ativo")
    private Boolean ativo = true;
}

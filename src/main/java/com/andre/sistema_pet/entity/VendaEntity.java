package com.andre.sistema_pet.entity;

import com.andre.sistema_pet.enums.FormaPagamento;
import com.andre.sistema_pet.enums.TipoCartao;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tb_venda")
public class VendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long idVenda;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVenda = new Date();

    private String status;

    private Double totalBruto;
    private Double totalDesconto;
    private Double totalVenda;
    private Double descontoGeral;
    private Double arredondamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVendaEntity> itens;

    @Enumerated(EnumType.STRING)
    private TipoCartao tipoCartao;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    private Integer numeroCheque;

    private Integer bancoCheque;

    @Temporal(TemporalType.DATE)
    private Date dataCompensacaoCheque;

    @Column(name = "versao")
    private LocalDateTime dataCriacao = LocalDateTime.now();
}

package com.andre.sistema_pet.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_item_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String categoria;
    private Double precoUnitario;
    private String descricao;
    private Integer quantidade;
    private Double desconto;
    private Double totalSemDesconto;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    private VendaEntity venda;
}

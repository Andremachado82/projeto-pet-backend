package com.andre.sistema_pet.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "tb_atendimento")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_atendimento")
    private LocalDate dataAtendimento;

    @Column(name = "hora_atendimento")
    private LocalTime horaAtendimento;

    @Column(name = "tipo_atendimento")
    private String tipoAtendimento;

    @Column(name = "descricao_atendimento")
    private String descricaoAtendimento;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private PetEntity pet;

    @Column(name = "valor_atendimento")
    private Double valorAtendimento;

    @Column(name = "ativo")
    private Boolean ativo = true;
}

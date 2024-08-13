package com.andre.sistema_pet.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "tb_pet")
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "especie_id")
    private EspecieEntity especie;

    @ManyToOne
    @JoinColumn(name = "raca_id")
    private RacaEntity raca;

    private String sexo;
    private String idade;
    private String statusDeSaude;
    private LocalDate ultimaVisitaVeterinaria;
    private LocalDate dataNascimento;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = true)
    private ClienteEntity cliente;

    @Column(name = "ativo")
    private Boolean ativo = true;

    public PetEntity() {
    }

    public PetEntity(Long id) {
        this.id = id;
    }
}
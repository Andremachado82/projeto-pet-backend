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
    private String especie;
    private String sexo;
    private String raca;
    private String idade;
    private String statusDeSaude;
    private LocalDate ultimaVisitaVeterinaria;
    private LocalDate dataNascimento;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;
}

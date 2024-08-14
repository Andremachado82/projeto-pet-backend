package com.andre.sistema_pet.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "tb_vacina")
public class VacinaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String tipo;
    private String fabricante;
    private String lote;
    private LocalDate dataValidade;

    @Column(name = "ativo")
    private Boolean ativo = true;
}
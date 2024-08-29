package com.andre.sistema_pet.entity;

import com.andre.sistema_pet.enums.TipoVacina;
import com.andre.sistema_pet.enums.TipoVermifugo;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "tb_vermifugo")
public class VermifugoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoVermifugo tipo;

    private String fabricante;
    private String lote;
    private LocalDate dataValidade;

    @Column(name = "ativo")
    private Boolean ativo = true;
}
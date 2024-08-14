package com.andre.sistema_pet.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_especie")
public class EspecieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeEspecie;

    @Column(name = "ativo")
    private Boolean ativo = true;
}

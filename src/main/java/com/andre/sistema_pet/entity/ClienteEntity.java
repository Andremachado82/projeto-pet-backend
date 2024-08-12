package com.andre.sistema_pet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_cliente")
public class ClienteEntity extends Pessoa {

    @OneToMany(mappedBy = "cliente")
    private List<PetEntity> pets;

    @OneToMany(mappedBy = "cliente")
    private List<AtendimentoEntity> atendimentos;

    @Column(name = "ativo")
    private Boolean ativo = true;

    @Column(name = "versao")
    private LocalDateTime dataCriacao;

    public ClienteEntity() {
    }

    public ClienteEntity(Long id) {
        this.setId(id);
    }
}
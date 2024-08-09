package com.andre.sistema_pet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    public ClienteEntity() {
    }

    public ClienteEntity(Long id) {
        this.setId(id);
    }
}
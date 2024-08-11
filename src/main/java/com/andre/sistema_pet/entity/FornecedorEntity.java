package com.andre.sistema_pet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_fornecedor")
public class FornecedorEntity extends Pessoa {

    private String inscricaoEstadual;
    private List<String> produtosServicosOferecidos;
}

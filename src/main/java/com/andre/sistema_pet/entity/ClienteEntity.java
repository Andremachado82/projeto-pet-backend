package com.andre.sistema_pet.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String nomeFantasia;
    private String cep;
    private String telefone;
    private String email;
    private String cpfCnpj;
    private String logradouro;
    private String nro;
    private String bairro;
    private String cidade;
    private String estado;
}

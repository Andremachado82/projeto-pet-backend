package com.andre.sistema_pet.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class Pessoa {

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
    private String localidade;
    private String uf;
}

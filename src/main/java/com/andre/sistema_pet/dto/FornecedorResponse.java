package com.andre.sistema_pet.dto;

import lombok.Data;

@Data
public class FornecedorResponse {

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
    private String inscricaoEstadual;
}

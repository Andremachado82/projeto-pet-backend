package com.andre.sistema_pet.dto;

import lombok.Data;

import java.util.List;

@Data
public class FornecedorRequest {

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
    private List<String> produtos;
}

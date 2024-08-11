package com.andre.sistema_pet.dto;

import com.andre.sistema_pet.entity.InformacoesBancarias;
import lombok.Data;

import java.util.List;

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
    private List<String> produtosServicosOferecidos;
//    private String condicoesPagamento;
//    private String prazoEntrega;
//    private double limiteCredito;
//    private InformacoesBancarias informacoesBancarias;
}

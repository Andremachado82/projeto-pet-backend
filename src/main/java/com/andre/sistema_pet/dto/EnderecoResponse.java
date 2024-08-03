package com.andre.sistema_pet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoResponse {

    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String complemento;
    private String unidade;
    private String localidade;
    private String ibge;
    private String uf;
    private String gia;
    private String ddd;
    private String siafi;
    private String erro;
}

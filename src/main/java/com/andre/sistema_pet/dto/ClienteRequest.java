package com.andre.sistema_pet.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ClienteRequest {

    @NotEmpty(message = "Nome é obrigatório")
    private String nome;

    private String nomeFantasia;

    @NotEmpty(message = "CEP é obrigatório")
    private String cep;

    @NotEmpty(message = "Telefone é obrigatório")
    private String telefone;

    private String email;

    @NotEmpty(message = "CPF/CNPJ é obrigatório")
    private String cpfCnpj;

    @NotEmpty(message = "Logradouro é obrigatório")
    private String logradouro;

    @NotEmpty(message = "Número é obrigatório")
    private String nro;

    @NotEmpty(message = "Bairro é obrigatório")
    private String bairro;

    @NotEmpty(message = "Cidade é obrigatória")
    private String cidade;

    @NotEmpty(message = "Estado é obrigatório")
    private String estado;
}

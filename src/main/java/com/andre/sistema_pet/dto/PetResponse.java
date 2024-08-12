package com.andre.sistema_pet.dto;

import lombok.Data;

@Data
public class PetResponse {

    private Long id;
    private String nome;
    private String especie;
    private String sexo;
    private String raca;
    private String idade;
    private String statusDeSaude;
    private String ultimaVisitaVeterinaria;
    private String dataNascimento;
    private String nomeCliente;
    private String observacoes;
    private Long idCliente;
    private Boolean ativo;
}

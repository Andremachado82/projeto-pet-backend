package com.andre.sistema_pet.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PetRequest {

    @NotEmpty(message = "Nome é obrigatório")
    private String nome;

    @NotEmpty(message = "Espécie é obrigatório")
    private String especie;

    @NotEmpty(message = "Sexo é obrigatório")
    private String sexo;

    private String raca;

    private String idade;

    private String statusDeSaude;

    private LocalDate ultimaVisitaVeterinaria;

    private LocalDate dataNascimento;

    private String observacoes;

    @NotNull(message = "IdCliente é obrigatório")
    private Long idCliente;
}

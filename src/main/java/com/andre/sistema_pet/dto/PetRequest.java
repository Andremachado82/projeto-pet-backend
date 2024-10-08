package com.andre.sistema_pet.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PetRequest {

    @NotEmpty(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Espécie é obrigatório")
    private Long idEspecie;

    @NotEmpty(message = "Sexo é obrigatório")
    private String sexo;

    @NotNull(message = "Raça é obrigatório")
    private Long idRaca;

    private String idade;

    private String statusDeSaude;

    private LocalDate ultimaVisitaVeterinaria;

    private LocalDate dataNascimento;

    private String observacoes;

    private Long idCliente;

    private Boolean ativo;
}

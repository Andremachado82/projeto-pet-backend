package com.andre.sistema_pet.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RacaRequest {

    @NotEmpty(message = "Nome é obrigatório")
    private String nome;
}

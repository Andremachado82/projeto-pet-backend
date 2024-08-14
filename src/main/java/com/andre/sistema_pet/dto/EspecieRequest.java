package com.andre.sistema_pet.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EspecieRequest {

    @NotEmpty(message = "Nome é obrigatório")
    private String nomeEspecie;
    private Boolean ativo;
}

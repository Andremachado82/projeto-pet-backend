package com.andre.sistema_pet.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VacinaRequest {

    @NotEmpty(message = "Nome é obrigatório")
    private String nome;

    @NotEmpty(message = "Tipo é obrigatório")
    private String tipo;

    @NotEmpty(message = "Lote é obrigatório")
    private String lote;

    @NotEmpty(message = "Fabricante é obrigatório")
    private String fabricante;

    @NotNull(message = "Data validade é obrigatório")
    private LocalDate dataValidade;

    private Boolean ativo;
}

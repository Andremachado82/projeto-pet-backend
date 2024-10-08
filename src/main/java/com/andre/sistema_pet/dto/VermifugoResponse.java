package com.andre.sistema_pet.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VermifugoResponse {

    private Long id;
    private String nome;
    private String tipo;
    private String fabricante;
    private String lote;
    private LocalDate dataValidade;
    private Boolean ativo;
}

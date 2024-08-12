package com.andre.sistema_pet.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AtendimentoResponse {

    private Long id;
    private LocalDate dataAtendimento;
    private LocalTime horaAtendimento;
    private String tipoAtendimento;
    private String descricaoAtendimento;
    private String status;
    private ClienteResponse cliente;
    private PetResponse pet;
    private Double valorAtendimento;
    private Boolean ativo;
}

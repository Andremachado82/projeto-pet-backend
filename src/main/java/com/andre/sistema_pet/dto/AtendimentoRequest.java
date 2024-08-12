package com.andre.sistema_pet.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AtendimentoRequest {

    private LocalDate dataAtendimento;
    private LocalTime horaAtendimento;
    private String tipoAtendimento;
    private String descricaoAtendimento;
    private String status;
    private Long clienteId;
    private Long petId;
    private Double valorAtendimento;
    private String metodoPagamento;
    private Boolean ativo;
}

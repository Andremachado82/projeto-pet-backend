package com.andre.sistema_pet.dto;

import lombok.Data;

@Data
public class EspecieResponse {

    private Long id;
    private String nomeEspecie;
    private Boolean ativo;
}

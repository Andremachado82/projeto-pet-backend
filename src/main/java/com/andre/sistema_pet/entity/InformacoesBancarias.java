package com.andre.sistema_pet.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class InformacoesBancarias {

    private String banco;
    private String agencia;
    private String contaCorrente;
    private String codigoBanco;
}

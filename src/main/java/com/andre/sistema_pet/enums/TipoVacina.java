package com.andre.sistema_pet.enums;

import com.andre.sistema_pet.exceptions.ResourceNotFoundException;

public enum TipoVacina {

    ANTIRRABICA("Antirrábica"),
    GIARDIA("Giardia"),
    GRIPE("Gripe"),
    LEISHMANIOSE("Leishmaniose"),
    LEPTOSPIROSE("Leptospirose"),
    POLIVALENTE("Polivalente"),
    QUADRUPLA("Quádrupla"),
    QUINTUPLA("Quíntupla"),
    TRIPLICE("Tríplice"),
    BRONCHISEPTICA("Bronchiseptica"),
    PARAINFLUENZA("Parainfluenza"),
    CORONAVIROSE("Coronavirose"),
    HEPATITE("Hepatite"),
    ADENOVIRUS("Adenovírus"),
    LYME("Lyme"),
    FELV("FELV"),
    FIV("FIV"),
    PIF("PIF"),
    CLAMIDIOSE("Clamidose"),
    DERMATOFITOSE("Dermatofitose");

    private final String descricao;

    TipoVacina(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoVacina getDescricaoTipoVacina(String descricao) {
        for (TipoVacina tipo : TipoVacina.values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new ResourceNotFoundException("Descrição não encontrada para o tipo vacina: " + descricao);
    }
}

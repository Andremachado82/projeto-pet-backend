package com.andre.sistema_pet.enums;

import com.andre.sistema_pet.exceptions.ResourceNotFoundException;

public enum TipoVermifugo {

    ANTIPARASITARIO_INTERNO("Antiparasitário Interno"),
    ANTIPARASITARIO_EXTERNO("Antiparasitário Externo"),
    ANTI_HELMINTICO("Anti-helmíntico"),
    ANTIPROTOZOARIO("Antiprotozoário"),
    COMBINADO("Combinado"),
    LARVICIDA("Larvicida"),
    ANTI_COCCIDIANO("Anti-coccidiano"),
    VERMIFUGO_AMPLA_ESPECTRO("Vermífugo Amplo Espectro");

    private final String descricao;

    TipoVermifugo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoVermifugo getDescricaoTipoVermifugo(String descricao) {
        for (TipoVermifugo tipo : TipoVermifugo.values()) {
            if (tipo.getDescricao().equals(descricao)) {
                return tipo;
            }
        }
        throw new ResourceNotFoundException("Descrição de tipo de vermífugo não encontrada: " + descricao);
    }
}

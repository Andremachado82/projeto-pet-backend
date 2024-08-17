package com.andre.sistema_pet.repository;

import com.andre.sistema_pet.entity.AtendimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AtendimentoRepository extends JpaRepository<AtendimentoEntity, Long> {
    List<AtendimentoEntity> findByDataAtendimento(LocalDate dataAtendimento);

    List<AtendimentoEntity> findByDataAtendimentoBetween(LocalDate dataInicio, LocalDate dataFim);
}

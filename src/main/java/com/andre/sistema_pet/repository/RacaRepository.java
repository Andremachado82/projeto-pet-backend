package com.andre.sistema_pet.repository;

import com.andre.sistema_pet.entity.ClienteEntity;
import com.andre.sistema_pet.entity.RacaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RacaRepository extends JpaRepository<RacaEntity, Long> {
}

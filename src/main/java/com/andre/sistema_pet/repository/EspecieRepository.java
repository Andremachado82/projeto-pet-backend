package com.andre.sistema_pet.repository;

import com.andre.sistema_pet.entity.EspecieEntity;
import com.andre.sistema_pet.entity.RacaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecieRepository extends JpaRepository<EspecieEntity, Long> {
}

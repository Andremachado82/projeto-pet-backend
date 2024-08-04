package com.andre.sistema_pet.repository;

import com.andre.sistema_pet.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<PetEntity, Long> {

    List<PetEntity> findByClienteId(Long clienteId);
}

package com.andre.sistema_pet.repository;

import com.andre.sistema_pet.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    List<ClienteEntity> findByNomeContainingIgnoreCase(String nome);
}

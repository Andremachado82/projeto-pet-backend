package com.andre.sistema_pet.repository;

import com.andre.sistema_pet.entity.ClienteEntity;
import com.andre.sistema_pet.entity.FornecedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<FornecedorEntity, Long> {

    List<FornecedorEntity> findByNomeContainingIgnoreCase(String nome);
}

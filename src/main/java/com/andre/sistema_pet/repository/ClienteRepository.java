package com.andre.sistema_pet.repository;

import com.andre.sistema_pet.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    @Query("SELECT c FROM ClienteEntity c LEFT JOIN FETCH c.pets")
    List<ClienteEntity> findAllWithPets();
}

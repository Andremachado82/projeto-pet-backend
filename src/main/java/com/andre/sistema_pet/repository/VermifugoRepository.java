package com.andre.sistema_pet.repository;

import com.andre.sistema_pet.entity.VacinaEntity;
import com.andre.sistema_pet.entity.VermifugoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VermifugoRepository extends JpaRepository<VermifugoEntity, Long> {

}

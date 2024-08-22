package com.andre.sistema_pet.repository;

import com.andre.sistema_pet.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

//    List<ProdutoEntity> findByProdutoId(Long produtoId);
}

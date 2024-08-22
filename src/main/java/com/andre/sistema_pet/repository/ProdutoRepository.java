package com.andre.sistema_pet.repository;

import com.andre.sistema_pet.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

//    List<ProdutoEntity> findByProdutoId(Long produtoId);

    List<ProdutoEntity> findByNomeContainingIgnoreCase(String nome);
}

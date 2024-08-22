package com.andre.sistema_pet.controller;

import com.andre.sistema_pet.dto.ProdutoRequest;
import com.andre.sistema_pet.dto.ProdutoResponse;
import com.andre.sistema_pet.dto.VacinaRequest;
import com.andre.sistema_pet.dto.VacinaResponse;
import com.andre.sistema_pet.service.ProdutoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponse> createProduto(@Valid @RequestBody ProdutoRequest request) {
        ProdutoResponse produto = produtoService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoResponse>> getAllClientes(
            @RequestParam(defaultValue = "0") @PositiveOrZero int pageIndex,
            @RequestParam(defaultValue = "20") @Positive @Max(100) int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<ProdutoResponse> produtos = produtoService.findAll(pageRequest);
        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> updateProduto(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequest request) {
        ProdutoResponse updatedProduto= produtoService.update(id, request);
        return ResponseEntity.ok(updatedProduto);
    }
}

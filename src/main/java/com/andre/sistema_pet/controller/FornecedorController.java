package com.andre.sistema_pet.controller;

import com.andre.sistema_pet.dto.ClienteResponse;
import com.andre.sistema_pet.dto.FornecedorRequest;
import com.andre.sistema_pet.dto.FornecedorResponse;
import com.andre.sistema_pet.service.FornecedorService;
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

import java.util.List;

@RestController
@RequestMapping("/api/fornecedores")
@CrossOrigin(origins = "http://localhost:4200")
public class FornecedorController {
    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<FornecedorResponse> createFornecedor(@Valid @RequestBody FornecedorRequest request) {
        FornecedorResponse fornecedor = fornecedorService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedor);
    }

    @GetMapping
    public ResponseEntity<Page<FornecedorResponse>> getAllFornecedores(@RequestParam(defaultValue = "0") @PositiveOrZero int pageIndex,
                                                        @RequestParam(defaultValue = "20") @Positive @Max(100) int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<FornecedorResponse> fornecedores = fornecedorService.findAll(pageRequest);
        return ResponseEntity.ok(fornecedores);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FornecedorResponse>> getFornecedoresPorNome(@RequestParam String nome) {
        List<FornecedorResponse> fornecedores = fornecedorService.findByName(nome);
        return ResponseEntity.ok(fornecedores);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorResponse> updateFornecedor(
            @PathVariable Long id,
            @Valid @RequestBody FornecedorRequest request) {
        FornecedorResponse updatedCliente = fornecedorService.update(id, request);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        fornecedorService.delete(id);
    }
}

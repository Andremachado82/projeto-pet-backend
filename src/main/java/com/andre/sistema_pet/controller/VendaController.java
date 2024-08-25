package com.andre.sistema_pet.controller;

import com.andre.sistema_pet.dto.VendaRequest;
import com.andre.sistema_pet.dto.VendaResponse;
import com.andre.sistema_pet.service.VendaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public VendaResponse save(@Valid @RequestBody VendaRequest request) {
        return vendaService.criarVenda(request);
    }

    @PostMapping("/finalizar")
    public ResponseEntity<String> finalizarVenda(@Valid @RequestBody VendaRequest request) {
        try {
            vendaService.finalizarVenda(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao finalizar a venda: " + e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<VendaResponse> atualizarVenda(@PathVariable Long id, @Valid @RequestBody VendaRequest request) {
        try {
            VendaResponse response = vendaService.atualizarVenda(id, request);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}

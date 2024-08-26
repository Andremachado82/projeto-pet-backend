package com.andre.sistema_pet.controller;

import com.andre.sistema_pet.dto.VendaRequest;
import com.andre.sistema_pet.dto.VendaResponse;
import com.andre.sistema_pet.service.VendaService;
import jakarta.persistence.EntityNotFoundException;
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

    @GetMapping
    public ResponseEntity<Page<VendaResponse>> getAllVendas(@RequestParam(defaultValue = "0") @PositiveOrZero int pageIndex,
                                                        @RequestParam(defaultValue = "20") @Positive @Max(100) int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<VendaResponse> vendas = vendaService.findAll(pageRequest);
        return ResponseEntity.ok(vendas);
    }
    @GetMapping("/{id}")
    public VendaResponse getVendaPorId(@PathVariable Long id) {
        return vendaService.getVendaPorId(id);
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

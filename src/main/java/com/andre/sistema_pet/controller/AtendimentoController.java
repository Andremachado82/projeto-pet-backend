package com.andre.sistema_pet.controller;

import com.andre.sistema_pet.dto.AtendimentoRequest;
import com.andre.sistema_pet.dto.AtendimentoResponse;
import com.andre.sistema_pet.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atendimentos")
@CrossOrigin(origins = "http://localhost:4200")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @PostMapping
    public ResponseEntity<AtendimentoResponse> createAtendimento(@RequestBody AtendimentoRequest request) {
        AtendimentoResponse response = atendimentoService.createAtendimento(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AtendimentoResponse>> getAllAtendimentos() {
        List<AtendimentoResponse> responses = atendimentoService.getAllAtendimentos();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoResponse> getAtendimentoById(@PathVariable Long id) {
        AtendimentoResponse response = atendimentoService.getAtendimentoById(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtendimentoResponse> updateAtendimento(@PathVariable Long id, @RequestBody AtendimentoRequest request) {
        AtendimentoResponse response = atendimentoService.updateAtendimento(id, request);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.andre.sistema_pet.controller;

import com.andre.sistema_pet.dto.*;
import com.andre.sistema_pet.service.RacaOrEspecieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class RacaOrEspecieController {

    @Autowired
    private RacaOrEspecieService racaOrEspecieService;

    @PostMapping("/racas")
    public ResponseEntity<RacaResponse> createRaca(@Valid @RequestBody RacaRequest request) {
        RacaResponse raca = racaOrEspecieService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(raca);
    }

    @PutMapping("racas/{id}")
    public ResponseEntity<RacaResponse> updateRaca(
            @PathVariable Long id,
            @Valid @RequestBody RacaRequest request) {
        RacaResponse updatedRaca = racaOrEspecieService.updateRaca(id, request);
        return ResponseEntity.ok(updatedRaca);
    }

    @PostMapping("/especies")
    public ResponseEntity<EspecieResponse> createEspecie(@Valid @RequestBody EspecieRequest request) {
        EspecieResponse especie = racaOrEspecieService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(especie);
    }

    @PutMapping("especies/{id}")
    public ResponseEntity<EspecieResponse> updateEspecie(
            @PathVariable Long id,
            @Valid @RequestBody EspecieRequest request) {
        EspecieResponse updatedEspecie = racaOrEspecieService.updateEspecie(id, request);
        return ResponseEntity.ok(updatedEspecie);
    }

    @GetMapping("/racas")
    public ResponseEntity<List<RacaResponse>> getAllRacas() {
        List<RacaResponse> racas = racaOrEspecieService.findAllRacas();
        return ResponseEntity.ok(racas);
    }

    @GetMapping("/especies")
    public ResponseEntity<List<EspecieResponse>> getAllEspecies() {
        List<EspecieResponse> especies = racaOrEspecieService.findAllEspecies();
        return ResponseEntity.ok(especies);
    }

    @PutMapping("/{id}/alterar-situacao")
    public ResponseEntity<Void> alterarSituacaoCliente(
            @PathVariable Long id,
            @RequestBody Map<String, Object> requestBody) {

        boolean resultado = racaOrEspecieService.alterarSituacaoCliente(id, requestBody);

        if (resultado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.andre.sistema_pet.controller;

import com.andre.sistema_pet.dto.*;
import com.andre.sistema_pet.service.PetService;
import com.andre.sistema_pet.service.RacaOrEspecieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class RacaController {

    @Autowired
    private RacaOrEspecieService racaOrEspecieService;

    @PostMapping("/racas")
    public ResponseEntity<RacaResponse> createRaca(@Valid @RequestBody RacaRequest request) {
        RacaResponse raca = racaOrEspecieService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(raca);
    }

    @PostMapping("/especies")
    public ResponseEntity<EspecieResponse> createEspecie(@Valid @RequestBody EspecieRequest request) {
        EspecieResponse especie = racaOrEspecieService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(especie);
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
}

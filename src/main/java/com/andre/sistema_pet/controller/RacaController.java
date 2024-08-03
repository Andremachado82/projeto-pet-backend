package com.andre.sistema_pet.controller;

import com.andre.sistema_pet.dto.PetRequest;
import com.andre.sistema_pet.dto.PetResponse;
import com.andre.sistema_pet.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "http://localhost:4200")
public class RacaController {
    @Autowired
    private PetService petService;

    @PostMapping
    public ResponseEntity<PetResponse> createPet(@Valid @RequestBody PetRequest request) {
        PetResponse pet = petService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(pet);
    }

    @GetMapping
    public ResponseEntity<List<PetResponse>> getAllPets() {
        List<PetResponse> pets = petService.findAll();
        return ResponseEntity.ok(pets);
    }
}

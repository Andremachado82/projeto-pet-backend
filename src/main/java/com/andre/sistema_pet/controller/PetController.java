package com.andre.sistema_pet.controller;

import com.andre.sistema_pet.dto.ClienteRequest;
import com.andre.sistema_pet.dto.PetRequest;
import com.andre.sistema_pet.dto.PetResponse;
import com.andre.sistema_pet.service.PetService;
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
@RequestMapping("/api/pets")
@CrossOrigin(origins = "http://localhost:4200")
public class PetController {
    @Autowired
    private PetService petService;

    @PostMapping
    public ResponseEntity<PetResponse> createPet(@Valid @RequestBody PetRequest request) {
        PetResponse pet = petService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(pet);
    }

    @GetMapping
    public ResponseEntity<Page<PetResponse>> getAllPets(@RequestParam(defaultValue = "0") @PositiveOrZero int pageIndex,
                                                        @RequestParam(defaultValue = "20") @Positive @Max(100) int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<PetResponse> pets = petService.findAll(pageRequest);
        return ResponseEntity.ok(pets);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetResponse> updatePet(
            @PathVariable Long id,
            @Valid @RequestBody PetRequest request) {
        PetResponse updatedCliente = petService.update(id, request);
        return ResponseEntity.ok(updatedCliente);
    }

    //    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        petService.delete(id);
//    }
    @PutMapping("/{petId}/alterar-situacao")
    public ResponseEntity<Void> alterarSituacaoCliente(
            @PathVariable Long petId,
            @RequestBody PetRequest petRequest) {

        boolean resultado = petService.alterarSituacaoCliente(petId, petRequest);

        if (resultado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

package com.andre.sistema_pet.controller;

import com.andre.sistema_pet.dto.VermifugoRequest;
import com.andre.sistema_pet.dto.VermifugoResponse;
import com.andre.sistema_pet.service.VermifugoService;
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
@RequestMapping("/api/vermifugos")
@CrossOrigin(origins = "http://localhost:4200")
public class VermifugoController {
    @Autowired
    private VermifugoService vermifugoService;

    @PostMapping
    public ResponseEntity<VermifugoResponse> createVermifugo(@Valid @RequestBody VermifugoRequest request) {
        VermifugoResponse vermifugo = vermifugoService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(vermifugo);
    }

    @GetMapping
    public ResponseEntity<Page<VermifugoResponse>> getAllVermifugos(@RequestParam(defaultValue = "0") @PositiveOrZero int pageIndex,
                                                        @RequestParam(defaultValue = "20") @Positive @Max(100) int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<VermifugoResponse> vermifugos = vermifugoService.findAll(pageRequest);
        return ResponseEntity.ok(vermifugos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VermifugoResponse> updateVermifugo(
            @PathVariable Long id,
            @Valid @RequestBody VermifugoRequest request) {
        VermifugoResponse updatedVermifugo = vermifugoService.update(id, request);
        return ResponseEntity.ok(updatedVermifugo);
    }

//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        vermifugoService.delete(id);
//    }

    @PutMapping("/{id}/alterar-situacao")
    public ResponseEntity<Void> alterarSituacaoVermifugo(
            @PathVariable Long id,
            @RequestBody VermifugoRequest request) {

        boolean resultado = vermifugoService.alterarSituacaoVermifugo(id, request);

        if (resultado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

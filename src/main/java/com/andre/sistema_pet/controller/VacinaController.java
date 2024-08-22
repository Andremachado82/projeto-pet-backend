package com.andre.sistema_pet.controller;

import com.andre.sistema_pet.dto.VacinaRequest;
import com.andre.sistema_pet.dto.VacinaResponse;
import com.andre.sistema_pet.service.VacinaService;
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
@RequestMapping("/api/vacinas")
@CrossOrigin(origins = "http://localhost:4200")
public class VacinaController {
    @Autowired
    private VacinaService vacinaService;

    @PostMapping
    public ResponseEntity<VacinaResponse> createVacina(@Valid @RequestBody VacinaRequest request) {
        VacinaResponse vacina = vacinaService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(vacina);
    }

    @GetMapping
    public ResponseEntity<Page<VacinaResponse>> getAllVacinas(@RequestParam(defaultValue = "0") @PositiveOrZero int pageIndex,
                                                        @RequestParam(defaultValue = "20") @Positive @Max(100) int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<VacinaResponse> vacinas = vacinaService.findAll(pageRequest);
        return ResponseEntity.ok(vacinas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VacinaResponse> updateVacina(
            @PathVariable Long id,
            @Valid @RequestBody VacinaRequest request) {
        VacinaResponse updatedVacina= vacinaService.update(id, request);
        return ResponseEntity.ok(updatedVacina);
    }

//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        vacinaService.delete(id);
//    }

    @PutMapping("/{id}/alterar-situacao")
    public ResponseEntity<Void> alterarSituacaoVacina(
            @PathVariable Long id,
            @RequestBody VacinaRequest request) {

        boolean resultado = vacinaService.alterarSituacaoVacina(id, request);

        if (resultado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

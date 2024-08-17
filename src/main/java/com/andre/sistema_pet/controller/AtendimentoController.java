package com.andre.sistema_pet.controller;

import com.andre.sistema_pet.dto.AtendimentoRequest;
import com.andre.sistema_pet.dto.AtendimentoResponse;
import com.andre.sistema_pet.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<Page<AtendimentoResponse>> getAtendimentos(
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<AtendimentoResponse> pageResult = atendimentoService.getAtendimentos(pageIndex, pageSize);
        return ResponseEntity.ok(pageResult);
    }


    @GetMapping("/data/{data}")
    public ResponseEntity<List<AtendimentoResponse>> buscarAgendamentosPorData(
            @PathVariable("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<AtendimentoResponse> atendimentos = atendimentoService.getAgendamentosPorData(data);
        return ResponseEntity.ok(atendimentos);
    }

    @GetMapping("/semana")
    public ResponseEntity<List<AtendimentoResponse>> buscarAgendamentosPorSemana(
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        List<AtendimentoResponse> atendimentos = atendimentoService.getAgendamentosPorSemana(dataInicio, dataFim);
        return ResponseEntity.ok(atendimentos);
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

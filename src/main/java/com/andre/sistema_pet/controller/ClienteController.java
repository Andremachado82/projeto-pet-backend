package com.andre.sistema_pet.controller;

import com.andre.sistema_pet.dto.ClienteRequest;
import com.andre.sistema_pet.dto.ClienteResponse;
import com.andre.sistema_pet.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponse> createCliente(@Valid @RequestBody ClienteRequest request) {
        ClienteResponse cliente = clienteService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> getAllClientes() {
        List<ClienteResponse> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ClienteResponse getClienteById(@PathVariable Long id) {
        return clienteService.getClienteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> updateCliente(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequest request) {
        ClienteResponse updatedCliente = clienteService.update(id, request);
        return ResponseEntity.ok(updatedCliente);
    }
}

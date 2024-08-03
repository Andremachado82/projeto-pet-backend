package com.andre.sistema_pet.controller;

import com.andre.sistema_pet.dto.EnderecoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/cep")
public class CepController {

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoResponse> buscarEndereco(@PathVariable String cep) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                // Converte a resposta JSON para EnderecoResponseDTO
                EnderecoResponse endereco = convertToEnderecoResponse(response.getBody());

                // Verifica se o CEP retornou algum erro
                if (endereco == null) {
                    return ResponseEntity.notFound().build();
                }

                return ResponseEntity.ok(endereco);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    private EnderecoResponse convertToEnderecoResponse(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Converte o JSON em EnderecoResponseDTO
            return objectMapper.readValue(json, EnderecoResponse.class);
        } catch (Exception e) {
            // Loga a exceção e retorna null em caso de erro
            e.printStackTrace();
            return null;
        }
    }
}

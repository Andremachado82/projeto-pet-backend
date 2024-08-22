package com.andre.sistema_pet.service;

import com.andre.sistema_pet.dto.EspecieRequest;
import com.andre.sistema_pet.dto.EspecieResponse;
import com.andre.sistema_pet.dto.RacaRequest;
import com.andre.sistema_pet.dto.RacaResponse;
import com.andre.sistema_pet.entity.EspecieEntity;
import com.andre.sistema_pet.entity.RacaEntity;
import com.andre.sistema_pet.exceptions.ResourceNotFoundException;
import com.andre.sistema_pet.repository.EspecieRepository;
import com.andre.sistema_pet.repository.RacaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RacaOrEspecieService {

    @Autowired
    private RacaRepository racaRepository;

    @Autowired
    private EspecieRepository especieRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<RacaResponse> findAllRacas() {
        return racaRepository.findAll().stream()
                .map(raca -> modelMapper.map(raca, RacaResponse.class))
                .collect(Collectors.toList());
    }

    public List<EspecieResponse> findAllEspecies() {
        return especieRepository.findAll().stream()
                .map(especie -> modelMapper.map(especie, EspecieResponse.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public RacaResponse create(RacaRequest request) {
        RacaEntity raca = modelMapper.map(request, RacaEntity.class);
        raca = racaRepository.save(raca);

        return modelMapper.map(raca, RacaResponse.class);
    }

    @Transactional
    public RacaResponse updateRaca(Long id, RacaRequest request) {
        RacaEntity raca = racaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Raça não encontrada com id " + id));

        BeanUtils.copyProperties(request, raca);

        raca = racaRepository.save(raca);

        return modelMapper.map(raca, RacaResponse.class);
    }

    @Transactional
    public EspecieResponse create(EspecieRequest request) {
        EspecieEntity especie = modelMapper.map(request, EspecieEntity.class);
        especie = especieRepository.save(especie);

        return modelMapper.map(especie, EspecieResponse.class);
    }

    @Transactional
    public EspecieResponse updateEspecie(Long id, EspecieRequest request) {
        EspecieEntity especie = especieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Espécie não encontrada com id " + id));

        BeanUtils.copyProperties(request, especie);

        especie = especieRepository.save(especie);

        return modelMapper.map(especie, EspecieResponse.class);
    }

    public boolean alterarSituacaoCliente(Long id, Map<String, Object> requestBody) {
        // Verifique se é uma EspecieRequest
        if (requestBody.containsKey("nomeEspecie")) {
            EspecieRequest especieRequest = new EspecieRequest();
            especieRequest.setNomeEspecie((String) requestBody.get("nomeEspecie"));
            especieRequest.setAtivo((Boolean) requestBody.get("ativo"));

            Optional<EspecieEntity> especieOptional = especieRepository.findById(id);
            if (especieOptional.isPresent()) {
                EspecieEntity especie = especieOptional.get();
                especie.setAtivo(!especieRequest.getAtivo());

                especieRepository.save(especie);

                return true;
            }
        } else if (requestBody.containsKey("nomeRaca")) {
            RacaRequest racaRequest = new RacaRequest();
            racaRequest.setNomeRaca((String) requestBody.get("nomeRaca"));
            racaRequest.setAtivo((Boolean) requestBody.get("ativo"));

            Optional<RacaEntity> racaOptional = racaRepository.findById(id);

            if (racaOptional.isPresent()) {
                RacaEntity raca = racaOptional.get();
                raca.setAtivo(!racaRequest.getAtivo());

                racaRepository.save(raca);

                return  true;
            }
        }
        return false;
    }
}

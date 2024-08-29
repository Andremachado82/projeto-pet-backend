package com.andre.sistema_pet.service;

import com.andre.sistema_pet.dto.VacinaRequest;
import com.andre.sistema_pet.dto.VacinaResponse;
import com.andre.sistema_pet.entity.VacinaEntity;
import com.andre.sistema_pet.enums.TipoVacina;
import com.andre.sistema_pet.exceptions.ResourceNotFoundException;
import com.andre.sistema_pet.repository.VacinaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VacinaService {

    @Autowired
    private VacinaRepository vacinaRepository;

    @Autowired
    private ModelMapper modelMapper;


    public Page<VacinaResponse> findAll(Pageable pageable) {
        Page<VacinaEntity> vacinasPage = vacinaRepository.findAll(pageable);
        return vacinasPage.map(vacinaEntity -> {
            VacinaResponse vacinaResponse = modelMapper.map(vacinaEntity, VacinaResponse.class);
            vacinaResponse.setTipo(vacinaEntity.getTipo().getDescricao());
            return vacinaResponse;
        });
    }

    @Transactional
    public VacinaResponse create(VacinaRequest request) {
        TipoVacina tipo = TipoVacina.valueOf(request.getTipo());

        VacinaEntity vacina = modelMapper.map(request, VacinaEntity.class);
        vacina.setTipo(tipo);
        vacina.setAtivo(true);
        vacina = vacinaRepository.save(vacina);

        VacinaResponse vacinaResponse = modelMapper.map(vacina, VacinaResponse.class);
        vacinaResponse.setTipo(tipo.getDescricao());
        return vacinaResponse;
    }

    @Transactional
    public VacinaResponse update(Long id, VacinaRequest request) {
        VacinaEntity vacina = vacinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vacina não encontrada com id " + id));

        BeanUtils.copyProperties(request, vacina);

        vacina.setTipo(TipoVacina.getDescricaoTipoVacina(request.getTipo()));
        vacina = vacinaRepository.save(vacina);

        VacinaResponse vacinaResponse = modelMapper.map(vacina, VacinaResponse.class);
        vacinaResponse.setTipo(vacina.getTipo().getDescricao());

        return vacinaResponse;
    }

//    @Transactional
//    public void delete(Long id) {
//        if (vacinaRepository.existsById(id)) {
//            vacinaRepository.deleteById(id);
//        } else {
//            throw new ResourceNotFoundException("Vacina com ID " + id + " não encontrada.");
//        }
//    }

    public boolean alterarSituacaoVacina(Long id, VacinaRequest request) {
        Optional<VacinaEntity> vacinaEntityOptional = vacinaRepository.findById(id);
        if (vacinaEntityOptional.isPresent()) {
            VacinaEntity vacina = vacinaEntityOptional.get();
            vacina.setAtivo(!request.getAtivo());

            vacinaRepository.save(vacina);

            return true;
        }
        return false;
    }

}

package com.andre.sistema_pet.service;

import com.andre.sistema_pet.dto.VermifugoRequest;
import com.andre.sistema_pet.dto.VermifugoResponse;
import com.andre.sistema_pet.entity.VermifugoEntity;
import com.andre.sistema_pet.enums.TipoVermifugo;
import com.andre.sistema_pet.exceptions.ResourceNotFoundException;
import com.andre.sistema_pet.repository.VacinaRepository;
import com.andre.sistema_pet.repository.VermifugoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VermifugoService {

    @Autowired
    private VermifugoRepository vermifugoRepository;

    @Autowired
    private ModelMapper modelMapper;


    public Page<VermifugoResponse> findAll(Pageable pageable) {
        Page<VermifugoEntity> vermifugosPage = vermifugoRepository.findAll(pageable);
        return vermifugosPage.map(vermifugoEntity -> {
            VermifugoResponse response = modelMapper.map(vermifugoEntity, VermifugoResponse.class);
            response.setTipo(vermifugoEntity.getTipo().getDescricao());
            return response;
        });
    }

    @Transactional
    public VermifugoResponse create(VermifugoRequest request) {
        TipoVermifugo tipo = TipoVermifugo.valueOf(request.getTipo());

        VermifugoEntity vermifugo = modelMapper.map(request, VermifugoEntity.class);
        vermifugo.setTipo(tipo);
        vermifugo.setAtivo(true);
        vermifugo = vermifugoRepository.save(vermifugo);

        VermifugoResponse vermifugoResponse = modelMapper.map(vermifugo, VermifugoResponse.class);
        vermifugoResponse.setTipo(tipo.getDescricao());
        return vermifugoResponse;
    }

    @Transactional
    public VermifugoResponse update(Long id, VermifugoRequest request) {
        VermifugoEntity vermifugo = vermifugoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vermífugo não encontrado com id " + id));

        BeanUtils.copyProperties(request, vermifugo);

        vermifugo.setTipo(TipoVermifugo.getDescricaoTipoVermifugo(request.getTipo()));
        vermifugo = vermifugoRepository.save(vermifugo);

        VermifugoResponse vermifugoResponse = modelMapper.map(vermifugo, VermifugoResponse.class);
        vermifugoResponse.setTipo(vermifugo.getTipo().getDescricao());

        return vermifugoResponse;
    }

//    @Transactional
//    public void delete(Long id) {
//        if (vermifugoRepository.existsById(id)) {
//            vermifugoRepository.deleteById(id);
//        } else {
//            throw new ResourceNotFoundException("Vacina com ID " + id + " não encontrada.");
//        }
//    }

    public boolean alterarSituacaoVermifugo(Long id, VermifugoRequest request) {
        Optional<VermifugoEntity> vermifugoEntityOptional = vermifugoRepository.findById(id);
        if (vermifugoEntityOptional.isPresent()) {
            VermifugoEntity vermifugo = vermifugoEntityOptional.get();
            vermifugo.setAtivo(!request.getAtivo());

            vermifugoRepository.save(vermifugo);

            return true;
        }
        return false;
    }


}

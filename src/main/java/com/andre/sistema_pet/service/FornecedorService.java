package com.andre.sistema_pet.service;

import com.andre.sistema_pet.dto.FornecedorRequest;
import com.andre.sistema_pet.dto.FornecedorResponse;
import com.andre.sistema_pet.entity.FornecedorEntity;
import com.andre.sistema_pet.exceptions.ResourceNotFoundException;
import com.andre.sistema_pet.repository.FornecedorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ModelMapper modelMapper;


    public Page<FornecedorResponse> findAll(PageRequest pageRequest) {
        Page<FornecedorEntity> fornecedoresPage = fornecedorRepository.findAll(pageRequest);
        return fornecedoresPage.map(fornecedor -> modelMapper.map(fornecedor, FornecedorResponse.class));
    }

    @Transactional
    public FornecedorResponse create(FornecedorRequest request) {
        FornecedorEntity fornecedor = modelMapper.map(request, FornecedorEntity.class);

        fornecedor = fornecedorRepository.save(fornecedor);

        return modelMapper.map(fornecedor, FornecedorResponse.class);
    }

    @Transactional
    public FornecedorResponse update(Long id, FornecedorRequest request) {
        FornecedorEntity fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado com id " + id));

        modelMapper.map(request, fornecedor);

        fornecedor = fornecedorRepository.save(fornecedor);

        return modelMapper.map(fornecedor, FornecedorResponse.class);
    }

    @Transactional
    public void delete(Long id) {
        if (fornecedorRepository.existsById(id)) {
            fornecedorRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Pet com ID " + id + " não encontrado.");
        }
    }

}

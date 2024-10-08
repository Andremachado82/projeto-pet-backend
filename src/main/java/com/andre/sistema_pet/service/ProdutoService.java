package com.andre.sistema_pet.service;

import com.andre.sistema_pet.dto.ProdutoRequest;
import com.andre.sistema_pet.dto.ProdutoResponse;
import com.andre.sistema_pet.entity.FornecedorEntity;
import com.andre.sistema_pet.entity.ProdutoEntity;
import com.andre.sistema_pet.exceptions.ResourceNotFoundException;
import com.andre.sistema_pet.repository.FornecedorRepository;
import com.andre.sistema_pet.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ModelMapper modelMapper;


    public Page<ProdutoResponse> findAll(PageRequest pageRequest) {
        Page<ProdutoEntity> produtosPage = produtoRepository.findAll(pageRequest);

        return produtosPage.map(produtoEntity -> modelMapper.map(produtoEntity, ProdutoResponse.class));
    }

    @Transactional
    public ProdutoResponse create(ProdutoRequest request) {
        // Mapear o request para ProdutoEntity
        ProdutoEntity produto = modelMapper.map(request, ProdutoEntity.class);

        // Verificar se o fornecedorId foi fornecido e obter o fornecedor
        FornecedorEntity fornecedor = null;
        if (request.getFornecedorId() != null) {
            fornecedor = fornecedorRepository.findById(request.getFornecedorId())
                    .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
        }
        produto.setFornecedor(fornecedor);

        // Salvar o produto
        produto = produtoRepository.save(produto);

        // Mapear e retornar a resposta
        return modelMapper.map(produto, ProdutoResponse.class);
    }

    @Transactional
    public ProdutoResponse update(Long id, ProdutoRequest request) {
        ProdutoEntity produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrada com id " + id));

        if (request.getFornecedorId() != null ) {
            FornecedorEntity fornecedor = fornecedorRepository.findById(request.getFornecedorId())
                    .orElse(null);
            produto.setFornecedor(fornecedor);
        }

        BeanUtils.copyProperties(request, produto);

        produto = produtoRepository.save(produto);

        return modelMapper.map(produto, ProdutoResponse.class);
    }

    public List<ProdutoResponse> findByNome(String nome) {
        List<ProdutoEntity> produtos = produtoRepository.findByNomeContainingIgnoreCase(nome);
        return produtos.stream()
                .map(produto -> modelMapper.map(produto, ProdutoResponse.class))
                .collect(Collectors.toList());
    }

    //    @Transactional
//    public void delete(Long id) {
//        if (produtoRepository.existsById(id)) {
//            produtoRepository.deleteById(id);
//        } else {
//            throw new ResourceNotFoundException("Pet com ID " + id + " não encontrado.");
//        }
//    }
//    public boolean alterarSituacaoPet(Long petId, PetRequest petRequest) {
//        Optional<ProdutoEntity> petEntityOptional = produtoRepository.findById(petId);
//        if (petEntityOptional.isPresent()) {
//            ProdutoEntity pet = petEntityOptional.get();
//            pet.setAtivo(!petRequest.getAtivo());
//
//            produtoRepository.save(pet);
//
//            return true;
//        }
//        return false;
//    }


}

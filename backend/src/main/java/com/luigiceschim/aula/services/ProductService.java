package com.luigiceschim.aula.services;

import com.luigiceschim.aula.dto.CategoryDTO;
import com.luigiceschim.aula.dto.ProductDTO;
import com.luigiceschim.aula.entities.Product;
import com.luigiceschim.aula.repositories.ProductRepository;
import com.luigiceschim.aula.services.exceptions.DatabaseException;
import com.luigiceschim.aula.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
        var productPage = repository.findAll(pageRequest);

        return productPage.map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
       var entity = repository.findById(id);
        return entity.map(result -> new ProductDTO(result,result.getCategories())).orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        //entity.setName(dto);

         repository.save(entity);

        return new ProductDTO(entity);


    }
    @Transactional
    public ProductDTO update(Long id,ProductDTO dto) {
        try {
            Product entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            var save= repository.save(entity);

            return new ProductDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("ID not found " + id);
        }





    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Id não encontrado");
        }
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação de integridade");
        }
    }
}

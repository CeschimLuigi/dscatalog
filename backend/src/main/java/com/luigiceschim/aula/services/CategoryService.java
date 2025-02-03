package com.luigiceschim.aula.services;

import com.luigiceschim.aula.dto.CategoryDTO;
import com.luigiceschim.aula.entities.Category;
import com.luigiceschim.aula.repositories.CategoryRepository;
import com.luigiceschim.aula.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO>findAll(){
        var list = repository.findAll();

        return list.stream().map(CategoryDTO::new).toList() ;
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
       var entity = repository.findById(id);
        return entity.map(CategoryDTO::new).orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category(dto);
         repository.save(entity);

        return new CategoryDTO(entity);


    }
    @Transactional
    public CategoryDTO update(Long id,CategoryDTO dto) {
        try {
            Category entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            var save= repository.save(entity);

            return new CategoryDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("ID not found " + id);
        }



    }
}

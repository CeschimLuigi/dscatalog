package com.luigiceschim.aula.services;

import com.luigiceschim.aula.entities.Category;
import com.luigiceschim.aula.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category>findAll(){
        return repository.findAll();
    }
}

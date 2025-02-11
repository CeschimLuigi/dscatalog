package com.luigiceschim.aula.repositories;

import com.luigiceschim.aula.entities.Product;
import com.luigiceschim.aula.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;


@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    private long existingId;
    private long countTotalProducts;
    private long noExistId;
    private long ExistId;




    @BeforeEach
    void setUp() throws Exception {
        existingId =1L;
        countTotalProducts = 25L;
        noExistId = 999L;




    }


    @Test 
    public void deleteShouldDeleteObjectWhenIdExists(){

        repository.deleteById(existingId);
        var result =  repository.findById(existingId);

        Assertions.assertFalse(result.isPresent());
        
    }

    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdIsNull(){
        Product product = Factory.createProduct();
        product.setId(null);

        var result = repository.save(product);

        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(countTotalProducts + 1, product.getId());
    }

    @Test
    public void findByIdShouldReturnNotEmptyOptionalProductWhenIdExists(){

        Optional<Product> result = repository.findById(existingId);

        Assertions.assertTrue(result.isPresent());


    } @Test
    public void findByIdShouldReturnEmptyOptionalProductWhenIdNotExists(){

        Optional<Product> result = repository.findById(noExistId);

        Assertions.assertTrue(result.isEmpty());



    }



}

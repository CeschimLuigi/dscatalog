package com.luigiceschim.aula.tests;

import com.luigiceschim.aula.dto.ProductDTO;
import com.luigiceschim.aula.entities.Category;
import com.luigiceschim.aula.entities.Product;

import java.time.Instant;

public class Factory {
    public static Product createProduct(){
        Product product= new Product(1L, Instant.parse("2020-10-20T03:00:00Z"),"https://img.com/img.png",800.00,"good phone","phone");
        product.getCategories().add(new Category(2L,"Eletronics"));
        return product;
    }

    public static ProductDTO createProductDTO(){
        Product product = createProduct();
        return new ProductDTO(product, product.getCategories());
    }
}

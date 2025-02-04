package com.luigiceschim.aula.repositories;


import com.luigiceschim.aula.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}

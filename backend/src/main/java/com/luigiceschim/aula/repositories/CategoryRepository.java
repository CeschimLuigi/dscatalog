package com.luigiceschim.aula.repositories;


import com.luigiceschim.aula.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}

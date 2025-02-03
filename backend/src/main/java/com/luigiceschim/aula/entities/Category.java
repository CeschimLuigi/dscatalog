package com.luigiceschim.aula.entities;

import com.luigiceschim.aula.dto.CategoryDTO;
import jakarta.persistence.*;


import java.util.Objects;

@Entity
@Table(name = "tb_category")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Category() {
    }

    public Category(CategoryDTO name) {
        this.name = name.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

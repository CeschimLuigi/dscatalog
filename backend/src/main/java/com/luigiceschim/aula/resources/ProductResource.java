package com.luigiceschim.aula.resources;

import com.luigiceschim.aula.dto.ProductDTO;
import com.luigiceschim.aula.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;


    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable){


        var pageService = service.findAllPaged(pageable);

        return ResponseEntity.ok().body(pageService);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        var dto = service.findById(id);

        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto){
        var result = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(result);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto){
        var result = service.update(id,dto);


        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    
}

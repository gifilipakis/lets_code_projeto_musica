package com.example.musicas.controllers;

import com.example.musicas.dto.FactoryDTO;
import com.example.musicas.dto.CategoryDTO;
import com.example.musicas.models.Category;
import com.example.musicas.services.CategoryService;
import com.example.musicas.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("/{uid}")
    public ResponseEntity findByUid(@PathVariable(value = "uid") String uid) {
        try {
            return ResponseEntity.ok(FactoryDTO.entityToDto(categoryService.findByUid(uid)));
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CategoryDTO categoryDTO) {
        categoryDTO.setUid(UUID.randomUUID().toString());
        Category category = FactoryDTO.dtoToEntity(categoryDTO);
        try {
            categoryService.create(category);
            return ResponseEntity.ok(categoryDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody CategoryDTO categoryDTO) {
        Category category = FactoryDTO.dtoToEntity(categoryDTO);
        try {
            categoryService.update(category);
            return ResponseEntity.ok(FactoryDTO.entityToDto(category));
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity delete(@PathVariable(value = "uid") String uid) {
        try {
            categoryService.delete(uid);
            return ResponseEntity.ok().build();
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

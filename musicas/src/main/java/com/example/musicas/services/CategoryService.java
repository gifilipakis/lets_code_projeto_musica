package com.example.musicas.services;

import com.example.musicas.models.Category;
import com.example.musicas.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public void create(Category category) {
        categoryRepository.saveAndFlush(category);
    }

    public Category findByUid(String uid) throws ChangeSetPersister.NotFoundException {
        Category category = categoryRepository.findByUid(uid).stream().findFirst().get();
        return category;
    }

    public Category update(Category category) throws ChangeSetPersister.NotFoundException {
        Category originalCategory = findByUid(category.getUid());
        originalCategory.setName(category.getName());
        categoryRepository.save(originalCategory);
        return originalCategory;
    }

    public void delete(String uid) throws ChangeSetPersister.NotFoundException {
        Category category = findByUid(uid);
        categoryRepository.delete(category);
    }

}

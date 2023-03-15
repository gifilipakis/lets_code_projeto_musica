package com.example.musicas.config;

import com.example.musicas.models.Category;
import com.example.musicas.models.Music;
import com.example.musicas.repositories.CategoryRepository;
import com.example.musicas.repositories.MusicRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;

@Configuration
@AllArgsConstructor
@Order(1)
public class CategoryConfig {
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void initBD() {
        Category category = new Category();
        category.setUid("8cff6b4d-b461-4c3c-8307-63c1d586b59c");
        category.setName("Categoria 1");
        categoryRepository.saveAndFlush(category);
    }

}

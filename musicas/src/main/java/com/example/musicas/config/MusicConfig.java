package com.example.musicas.config;

import com.example.musicas.models.Music;
import com.example.musicas.repositories.CategoryRepository;
import com.example.musicas.repositories.MusicRepository;
import com.example.musicas.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.data.crossstore.ChangeSetPersister;

import javax.annotation.PostConstruct;

@Configuration
@AllArgsConstructor
@Order(2)
public class MusicConfig {

    private MusicRepository musicRepository;
    private CategoryService categoryService;

    @PostConstruct
    public void initBD() throws ChangeSetPersister.NotFoundException {
        Music music1 = new Music();
        music1.setUid("f011da0a-74ba-4ceb-a796-383e754dfb6d");
        music1.setName("Música Número 1");
        music1.setAuthor("Autor da Música 1");
        music1.setCategory(categoryService.findByUid("8cff6b4d-b461-4c3c-8307-63c1d586b59c"));
        musicRepository.saveAndFlush(music1);
        Music music2 = new Music();
        music2.setUid("a98a1b4d-13e7-422d-8108-d1f8414a559b");
        music2.setName("Música Número 2");
        music2.setAuthor("Autor da Música 2");
        music2.setCategory(categoryService.findByUid("8cff6b4d-b461-4c3c-8307-63c1d586b59c"));
        musicRepository.saveAndFlush(music2);
    }

}

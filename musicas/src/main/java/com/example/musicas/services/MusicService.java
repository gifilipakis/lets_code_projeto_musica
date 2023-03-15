package com.example.musicas.services;

import com.example.musicas.models.Music;
import com.example.musicas.repositories.CategoryRepository;
import com.example.musicas.repositories.MusicRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MusicService {

    private MusicRepository musicRepository;
    private CategoryService categoryService;
    public void create(Music music) throws ChangeSetPersister.NotFoundException {
        music.setCategory(categoryService.update(music.getCategory()));
        musicRepository.saveAndFlush(music);
    }

    public Music findByUid(String uid) throws ChangeSetPersister.NotFoundException {
        Music music = musicRepository.findByUid(uid).stream().findFirst().get();
        return music;
    }

    public Music update(Music music) throws ChangeSetPersister.NotFoundException {
        Music originalMusic = findByUid(music.getUid());
        originalMusic.setName(music.getName());
        originalMusic.setAuthor(music.getAuthor());
        originalMusic.setLikes(music.getLikes());
        originalMusic.setCategory(categoryService.update(music.getCategory()));
        musicRepository.save(originalMusic);
        return originalMusic;
    }

    public void delete(String uid) throws ChangeSetPersister.NotFoundException {
        Music music = findByUid(uid);
        musicRepository.delete(music);
    }

    public void like(Music music) throws ChangeSetPersister.NotFoundException {
        music.like();
        update(music);
    }

    public void dislike(Music music) throws ChangeSetPersister.NotFoundException {
        music.dislike();
        update(music);
    }

}

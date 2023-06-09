package com.example.musicas.controllers;

import com.example.musicas.dto.FactoryDTO;
import com.example.musicas.dto.MusicDTO;
import com.example.musicas.models.Music;
import com.example.musicas.repositories.CategoryRepository;
import com.example.musicas.services.CategoryService;
import com.example.musicas.services.MusicService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/music")
@AllArgsConstructor
public class MusicController {

    private MusicService musicService;
    private CategoryRepository categoryRepository;

    @GetMapping("/{uid}")
    public ResponseEntity findByUid(@PathVariable(value = "uid") String uid) {
        try {
            return ResponseEntity.ok(FactoryDTO.entityToDto(musicService.findByUid(uid)));
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody MusicDTO musicDTO) throws ChangeSetPersister.NotFoundException {
        musicDTO.setUid(UUID.randomUUID().toString());
        Music music = FactoryDTO.dtoToEntity(musicDTO);
        try {
            musicService.create(music);
            return ResponseEntity.ok(musicDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody MusicDTO musicDTO) throws ChangeSetPersister.NotFoundException {
        Music music = FactoryDTO.dtoToEntity(musicDTO);
        try {
            musicService.update(music);
            return ResponseEntity.ok(FactoryDTO.entityToDto(music));
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity delete(@PathVariable(value = "uid") String uid) {
        try {
            musicService.delete(uid);
            return ResponseEntity.ok().build();
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

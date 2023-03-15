package com.example.musicas.controllers;

import com.example.musicas.dto.MusicDTO;
import com.example.musicas.dto.PlaylistDTO;
import com.example.musicas.dto.FactoryDTO;
import com.example.musicas.models.Playlist;
import com.example.musicas.services.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/playlist")
@AllArgsConstructor
public class PlaylistController {

    private PlaylistService playlistService;

    @GetMapping("/{uid}")
    public ResponseEntity findByUid(@PathVariable(value = "uid") String uid) {
        try {
            return ResponseEntity.ok(FactoryDTO.entityToDto(playlistService.findByUid(uid)));
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody PlaylistDTO playlistDTO) {
        playlistDTO.setUid(UUID.randomUUID().toString());
        Playlist playlist = FactoryDTO.dtoToEntity(playlistDTO);
        try {
            playlistService.create(playlist);
            return ResponseEntity.ok(playlistDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody PlaylistDTO playlistDTO) {
        Playlist playlist = FactoryDTO.dtoToEntity(playlistDTO);
        try {
            playlistService.update(playlist);
            return ResponseEntity.ok(FactoryDTO.entityToDto(playlist));
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/adicionar-musica/{uid}")
    public ResponseEntity addMusicToPlaylist(@PathVariable(value = "uid") String uid,
                                             @RequestBody MusicDTO musicDTO) throws ChangeSetPersister.NotFoundException {
        try {
            playlistService.addMusicToPlaylist(uid, FactoryDTO.dtoToEntity(musicDTO));
            return ResponseEntity.ok().build();
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/remover-musica/{uid}")
    public ResponseEntity removeMusicFromPlaylist(@PathVariable(value = "uid") String uid,
                                             @RequestBody MusicDTO musicDTO) throws ChangeSetPersister.NotFoundException {
        try {
            playlistService.removeMusicFromPlaylist(uid, FactoryDTO.dtoToEntity(musicDTO));
            return ResponseEntity.ok().build();
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity delete(@PathVariable(value = "uid") String uid) {
        try {
            playlistService.delete(uid);
            return ResponseEntity.ok().build();
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

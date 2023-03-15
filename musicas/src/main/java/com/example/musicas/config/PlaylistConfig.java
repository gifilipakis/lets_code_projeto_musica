package com.example.musicas.config;

import com.example.musicas.models.Category;
import com.example.musicas.models.Music;
import com.example.musicas.models.Playlist;
import com.example.musicas.repositories.CategoryRepository;
import com.example.musicas.repositories.PersonRepository;
import com.example.musicas.repositories.PlaylistRepository;
import com.example.musicas.services.MusicService;
import com.example.musicas.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.crossstore.ChangeSetPersister;

import javax.annotation.PostConstruct;

@Configuration
@AllArgsConstructor
@Order(3)
public class PlaylistConfig {

    private PlaylistRepository playlistRepository;
    private MusicService musicService;
    private PersonService personService;

    @PostConstruct
    public void initBD() throws ChangeSetPersister.NotFoundException {
        Playlist playlist = new Playlist();
        playlist.setUid("6c57be06-db67-4d96-9af0-e16296c6a337");
        playlist.setOwner(personService.findByUid("992c5b9d-feee-4545-9e12-58979e0bd2b8"));
        playlist.addMusicToPlaylist(musicService.findByUid("f011da0a-74ba-4ceb-a796-383e754dfb6d"));
        playlistRepository.saveAndFlush(playlist);
    }

}

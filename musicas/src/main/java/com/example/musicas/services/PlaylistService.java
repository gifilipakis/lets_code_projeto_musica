package com.example.musicas.services;

import com.example.musicas.models.Music;
import com.example.musicas.models.Playlist;
import com.example.musicas.repositories.PersonRepository;
import com.example.musicas.repositories.PlaylistRepository;
import com.example.musicas.repositories.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlaylistService {

    private PlaylistRepository playlistRepository;
    private PersonService personService;
    private MusicService musicService;

    public void create(Playlist playlist) throws ChangeSetPersister.NotFoundException {
        playlist.setOwner(personService.update(playlist.getOwner()));
        playlistRepository.saveAndFlush(playlist);
    }

    public Playlist findByUid(String uid) throws ChangeSetPersister.NotFoundException {
        Playlist playlist = playlistRepository.findByUid(uid).stream().findFirst().get();
        return playlist;
    }

    public Playlist update(Playlist playlist) throws ChangeSetPersister.NotFoundException {
        Playlist originalPlaylist = findByUid(playlist.getUid());
        originalPlaylist.setOwner(personService.update(playlist.getOwner()));
        originalPlaylist.setMusicCollection(playlist.getMusicCollection());
        playlistRepository.save(originalPlaylist);
        return originalPlaylist;
    }

    public void delete(String uid) throws ChangeSetPersister.NotFoundException {
        Playlist playlist = findByUid(uid);
        playlistRepository.delete(playlist);
    }

    public void addMusicToPlaylist(String uid, Music music) throws ChangeSetPersister.NotFoundException {
        Playlist playlist = findByUid(uid);
        playlist.addMusicToPlaylist(musicService.update(music));
        update(playlist);
    }

    public void removeMusicFromPlaylist(String uid, Music music) throws ChangeSetPersister.NotFoundException {
        Playlist playlist = findByUid(uid);
        playlist.removeMusicFromPlaylist(musicService.update(music));
        update(playlist);
    }

}

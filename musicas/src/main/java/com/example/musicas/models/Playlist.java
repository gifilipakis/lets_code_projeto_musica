package com.example.musicas.models;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
@Data
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String uid;
    @ManyToOne(fetch = FetchType.LAZY)
    private Person owner;
    @ManyToMany(targetEntity = Music.class)
    private Collection<Music> musicCollection = new ArrayList<>();

    public void addMusicToPlaylist(Music music) {
        musicCollection.add(music);
    }

    public void removeMusicFromPlaylist(Music music) {
        musicCollection.remove(music);
    }

}

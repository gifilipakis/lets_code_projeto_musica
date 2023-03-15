package com.example.musicas.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String uid;
    private String name;
    private String email;
    @OneToMany(mappedBy = "owner")
    private Collection<Playlist> playlists = new ArrayList<>();
    @ManyToMany(targetEntity = Music.class)
    private Collection<Music> likedMusicCollection = new ArrayList<>();

    public void addLikedMusic(Music music) {
        likedMusicCollection.add(music);
    }

    public void removeLikedMusic(Music music) {
        likedMusicCollection.remove(music);
    }

}

package com.example.musicas.models;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Data
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private UUID uid;
    @ManyToOne(fetch = FetchType.LAZY)
    private Person owner;
    @ManyToMany(targetEntity = Music.class)
    private Set musicSet;

}

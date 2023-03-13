package com.example.musicas.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private UUID uid;
    @OneToOne(fetch = FetchType.LAZY)
    private Person user;
    @ManyToMany(targetEntity = Music.class)
    private Set likedSet;

}

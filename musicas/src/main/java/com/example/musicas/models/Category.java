package com.example.musicas.models;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String uid;
    private String name;

    @OneToMany(mappedBy = "category")
    private Collection<Music> musicCollection = new ArrayList<>();

}

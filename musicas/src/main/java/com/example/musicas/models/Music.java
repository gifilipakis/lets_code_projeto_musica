package com.example.musicas.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private UUID uid;
    private String name;
    private String author;
    private int likes;
    @ManyToOne
    private Category categoria;

}

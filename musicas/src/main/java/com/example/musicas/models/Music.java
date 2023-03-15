package com.example.musicas.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
@Getter
@Setter
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String uid;
    private String name;
    private String author;
    private int likes;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    public void like() {
        likes++;
    }

    public void dislike() {
        likes--;
    }

}

package com.example.musicas.models;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private UUID uid;
    private String name;

}

package com.example.musicas.models;

import lombok.Data;

import javax.persistence.*;
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

}

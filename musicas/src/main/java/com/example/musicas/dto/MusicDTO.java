package com.example.musicas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MusicDTO {
    private String uid;
    private String name;
    private String author;
    private int likes;
    private CategoryDTO category;
}

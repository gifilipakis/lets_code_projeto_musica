package com.example.musicas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaylistDTO {
    private String uid;
    private PersonDTO owner;
}

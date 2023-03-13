package com.example.musicas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDTO {
    private UUID uid;
    private PersonDTO user;
    private Set likedSet;

}

package com.example.musicas.dto;

import com.example.musicas.models.Category;
import com.example.musicas.models.Person;
import com.example.musicas.models.Music;
import com.example.musicas.models.Playlist;
import com.example.musicas.services.CategoryService;
import org.springframework.data.crossstore.ChangeSetPersister;

public class FactoryDTO {

    public static Person dtoToEntity(PersonDTO personDTO) {
        Person person = new Person();
        person.setUid(personDTO.getUid());
        person.setName(personDTO.getName());
        person.setEmail(personDTO.getEmail());
        return person;
    }

    public static PersonDTO entityToDto(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setUid(person.getUid());
        personDTO.setName(person.getName());
        personDTO.setEmail(person.getEmail());
        return personDTO;
    }

    public static Music dtoToEntity(MusicDTO musicDTO) throws ChangeSetPersister.NotFoundException {
        Music music = new Music();
        music.setUid(musicDTO.getUid());
        music.setName(musicDTO.getName());
        music.setAuthor(musicDTO.getAuthor());
        music.setLikes(musicDTO.getLikes());
        music.setCategory(dtoToEntity(musicDTO.getCategory()));
        return music;
    }

    public static MusicDTO entityToDto(Music music) {
        MusicDTO musicDTO = new MusicDTO();
        musicDTO.setUid(music.getUid());
        musicDTO.setName(music.getName());
        musicDTO.setAuthor(music.getAuthor());
        musicDTO.setLikes(music.getLikes());
        musicDTO.setCategory(entityToDto(music.getCategory()));
        return musicDTO;
    }

    public static Category dtoToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setUid(categoryDTO.getUid());
        category.setName(categoryDTO.getName());
        return category;
    }

    public static CategoryDTO entityToDto(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setUid(category.getUid());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public static Playlist dtoToEntity(PlaylistDTO playlistDTO) {
        Playlist playlist = new Playlist();
        playlist.setUid(playlistDTO.getUid());
        playlist.setOwner(dtoToEntity(playlistDTO.getOwner()));
        return playlist;
    }

    public static PlaylistDTO entityToDto(Playlist playlist) {
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setUid(playlist.getUid());
        playlistDTO.setOwner(entityToDto(playlist.getOwner()));
        return playlistDTO;
    }
}

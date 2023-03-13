package com.example.musicas.dto;

import com.example.musicas.models.Person;

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
}

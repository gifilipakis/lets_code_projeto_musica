package com.example.musicas.controllers;

import com.example.musicas.dto.FactoryDTO;
import com.example.musicas.dto.PersonDTO;
import com.example.musicas.models.Person;
import com.example.musicas.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private PersonService personService;

    @GetMapping("/{uid}")
    public  ResponseEntity findByUid(@PathVariable(value = "uid") String uid) {
        try {
            return ResponseEntity.ok(FactoryDTO.entityToDto(personService.findByUid(uid)));
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody PersonDTO personDTO) {
        personDTO.setUid(UUID.randomUUID().toString());
        Person person = FactoryDTO.dtoToEntity(personDTO);
        try {
            personService.create(person);
            return ResponseEntity.ok(personDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody PersonDTO personDTO) {
        Person person = FactoryDTO.dtoToEntity(personDTO);
        try {
            personService.update(person);
            return ResponseEntity.ok(FactoryDTO.entityToDto(person));
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity delete(@PathVariable(value = "uid") String uid) {
        try {
            personService.delete(uid);
            return ResponseEntity.ok().build();
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.example.musicas.services;

import com.example.musicas.models.Person;
import com.example.musicas.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PersonService {

    private PersonRepository personRepository;

    public void create(Person person) {
        personRepository.saveAndFlush(person);
    }

    public Person findByUid(String uid) throws ChangeSetPersister.NotFoundException {
        Person person = personRepository.findByUid(uid).stream().findFirst().get();
        return person;
    }

    public Person update(Person person) throws ChangeSetPersister.NotFoundException {
        Person originalPerson = findByUid(person.getUid());
        originalPerson.setName(person.getName());
        originalPerson.setEmail(person.getEmail());
        personRepository.save(originalPerson);
        return originalPerson;
    }

    public void delete(String uid) throws ChangeSetPersister.NotFoundException {
        Person person = findByUid(uid);
        personRepository.delete(person);
    }
}

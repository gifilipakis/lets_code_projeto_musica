package com.example.musicas.config;

import com.example.musicas.models.Person;
import com.example.musicas.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@AllArgsConstructor
public class PersonConfig {

    private PersonRepository personRepository;

    @PostConstruct
    public void initBD() {
        Person person1 = new Person();
        person1.setUid("992c5b9d-feee-4545-9e12-58979e0bd2b8");
        person1.setName("Ana");
        person1.setEmail("ana@gmail.com");
        personRepository.saveAndFlush(person1);
        Person person2 = new Person();
        person2.setUid("acd86273-073a-4044-b34d-f85467881d7c");
        person2.setName("João");
        person2.setEmail("joao@gmail.com");
        personRepository.saveAndFlush(person2);
    }

}

package com.example.musicas.services;

import com.example.musicas.models.Music;
import com.example.musicas.models.Person;
import com.example.musicas.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class PersonService {

    private PersonRepository personRepository;
    private MusicService musicService;

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

    public void addLikedMusic(String uid, Music music) throws ChangeSetPersister.NotFoundException {
        Person person = findByUid(uid);
        if(!checkIfMusicLiked(person, music)) {
            musicService.like(music);
            person.addLikedMusic(musicService.update(music));
            update(person);
        }
    }

    public void removeLikedMusic(String uid, Music music) throws ChangeSetPersister.NotFoundException {
        Person person = findByUid(uid);
        musicService.dislike(music);
        person.removeLikedMusic(musicService.update(music));
        update(person);
    }

    public boolean checkIfMusicLiked(Person person, Music music) {
        Predicate<Music> checkLiked = m -> m.getUid().equals(music.getUid());
        for(Music m : person.getLikedMusicCollection()) {
            if(checkLiked.test(m)) {
                return true;
            }
        }
        return false;
    }
}

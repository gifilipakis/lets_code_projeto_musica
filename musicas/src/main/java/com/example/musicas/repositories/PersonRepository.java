package com.example.musicas.repositories;

import com.example.musicas.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p WHERE p.uid = :uid")
    List<Person> findByUid(@Param("uid") String uid);
}

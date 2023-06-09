package com.example.musicas.repositories;

import com.example.musicas.models.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Long> {
    @Query("SELECT m FROM Music m WHERE m.uid = :uid")
    List<Music> findByUid(@Param("uid") String uid);
}

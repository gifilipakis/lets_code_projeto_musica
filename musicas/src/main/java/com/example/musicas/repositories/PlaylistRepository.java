package com.example.musicas.repositories;

import com.example.musicas.models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    @Query("SELECT p FROM Playlist p WHERE p.uid = :uid")
    List<Playlist> findByUid(@Param("uid") String uid);
}

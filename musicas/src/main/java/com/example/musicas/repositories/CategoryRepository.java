package com.example.musicas.repositories;

import com.example.musicas.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.uid = :uid")
    List<Category> findByUid(@Param("uid") String uid);
}

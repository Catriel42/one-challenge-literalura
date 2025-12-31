package com.catodev.literalura.repository;

import com.catodev.literalura.model.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    Optional<AuthorEntity> findByName(String name);

    // Autores vivos en un año determinado
    @Query("SELECT a FROM AuthorEntity a WHERE a.birthYear <= :year " +
            "AND (a.deathYear IS NULL OR a.deathYear >= :year)")
    List<AuthorEntity> findAuthorsAliveInYear(@Param("year") Integer year);
}

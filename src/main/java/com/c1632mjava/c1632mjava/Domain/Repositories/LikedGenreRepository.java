package com.c1632mjava.c1632mjava.Domain.Repositories;

import com.c1632mjava.c1632mjava.Domain.Entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedGenreRepository extends JpaRepository<Genre, Long> {
}

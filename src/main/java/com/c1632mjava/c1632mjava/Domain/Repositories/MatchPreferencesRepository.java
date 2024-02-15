package com.c1632mjava.c1632mjava.Domain.Repositories;

import com.c1632mjava.c1632mjava.Domain.Entities.MatchPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchPreferencesRepository extends JpaRepository <MatchPreferences, Long> {
    //@Query("SELECT M FROM MatchesPreferences M WHERE M.User.userId = :id")
    Optional<MatchPreferences> findByUserId(Long id);
}

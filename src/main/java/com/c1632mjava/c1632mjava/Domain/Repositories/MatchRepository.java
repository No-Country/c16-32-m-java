package com.c1632mjava.c1632mjava.Domain.Repositories;

import com.c1632mjava.c1632mjava.Domain.Entities.Match;
import com.c1632mjava.c1632mjava.Domain.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findAllByUser1OrUser2(User user1, User user2);
    //TODO test que no te traiga los matches del user not logged.
}

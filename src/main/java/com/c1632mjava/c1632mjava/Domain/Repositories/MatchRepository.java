package com.c1632mjava.c1632mjava.Domain.Repositories;

import com.c1632mjava.c1632mjava.Domain.Entities.Match;
import com.c1632mjava.c1632mjava.Domain.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    Page<Match> findAllByUser1AndActiveIsTrueOrUser2AndActiveIsTrue(User user1, User user2, Pageable paging);
    List<Match> findAllByUser1UserId(Long userId1);
    //TODO test que no te traiga los matches del user not logged.
   Optional<Match> findByUser1UserIdAndUser2UserId(Long userId1, Long userId2);
}

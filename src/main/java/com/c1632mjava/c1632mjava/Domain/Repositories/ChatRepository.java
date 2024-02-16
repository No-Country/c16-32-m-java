package com.c1632mjava.c1632mjava.Domain.Repositories;

import com.c1632mjava.c1632mjava.Domain.Entities.Chat;
import com.c1632mjava.c1632mjava.Domain.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findAllBySender(User sender);
}

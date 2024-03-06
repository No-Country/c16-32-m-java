package com.c1632mjava.c1632mjava.Domain.Repositories;

import com.c1632mjava.c1632mjava.Domain.Entities.Chat;
import com.c1632mjava.c1632mjava.Domain.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    Page<Chat> findAllBySenderAndActiveIsTrue(User sender, Pageable paging);
    Page<Chat> findAllBySenderAndActiveIsTrueOrReceiverAndActiveIsTrue(User sender, User receiver, Pageable paging);
}

package com.c1632mjava.c1632mjava.Domain.Repositories;

import com.c1632mjava.c1632mjava.Domain.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    Page<User> findAllByActive(Boolean active, Pageable paging);
    Optional<User> findByEmail(String email);
    List<User> generateAlgorithm(Long loggedUserId);
    //Se hace un stored procedure en la base de datos y de acá se lo llama!!
}

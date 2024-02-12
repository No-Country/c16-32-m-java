package com.c1632mjava.c1632mjava.Domain.Repositories;

import com.c1632mjava.c1632mjava.Domain.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
}

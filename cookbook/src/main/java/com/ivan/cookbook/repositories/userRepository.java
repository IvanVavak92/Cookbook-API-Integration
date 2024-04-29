package com.ivan.cookbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

public interface userRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}

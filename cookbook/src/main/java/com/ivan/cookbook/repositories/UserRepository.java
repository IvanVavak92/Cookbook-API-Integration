package com.ivan.cookbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ivan.cookbook.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}

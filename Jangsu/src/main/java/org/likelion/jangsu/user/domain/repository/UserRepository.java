package org.likelion.jangsu.user.domain.repository;

import org.likelion.jangsu.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String userEmail);
}

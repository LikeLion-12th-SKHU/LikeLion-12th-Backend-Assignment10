package com.likelion.awss3_oauth.user.domain.repository;

import com.likelion.awss3_oauth.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String userEmail);
}

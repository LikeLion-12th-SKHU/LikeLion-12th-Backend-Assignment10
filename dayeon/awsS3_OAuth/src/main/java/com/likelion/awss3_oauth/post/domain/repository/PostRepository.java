package com.likelion.awss3_oauth.post.domain.repository;

import com.likelion.awss3_oauth.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long user_id);
}

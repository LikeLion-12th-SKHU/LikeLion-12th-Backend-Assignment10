package com.likelion.likelionassignment.post.domain.repository;

import com.likelion.likelionassignment.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

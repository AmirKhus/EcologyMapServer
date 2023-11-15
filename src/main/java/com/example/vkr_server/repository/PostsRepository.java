package com.example.vkr_server.repository;

import com.example.vkr_server.model.Posts;
import com.example.vkr_server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PostsRepository extends JpaRepository<Posts, Long> {

    List<Posts> findAllByPosterUser(User user);
}

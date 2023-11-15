package org.server.repository;

import org.server.model.Posts;
import org.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PostsRepository extends JpaRepository<Posts, Long> {

    List<Posts> findAllByPosterUser(User user);
}

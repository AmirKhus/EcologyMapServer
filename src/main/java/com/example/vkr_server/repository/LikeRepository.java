package com.example.vkr_server.repository;

import com.example.vkr_server.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    @Transactional
    Boolean existsByUsernameAndIdmarker(String username, Long idmarker);

    @Transactional
    void deleteByUsernameAndIdmarker(String username, Long idmarker);
}

package org.server.repository;

import org.server.model.Dislike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface DislikeRepository extends JpaRepository<Dislike, Long> {
    @Transactional
    Boolean existsByUsernameAndIdmarker(String username, Long idmarker);

    @Transactional
    void deleteByUsernameAndIdmarker(String username, Long idmarker);
}

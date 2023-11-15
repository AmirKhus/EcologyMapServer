package org.server.repository;

import org.server.model.Marker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MarkerRepository extends JpaRepository<Marker, Long> {
    @Override
    List<Marker> findAll();

    List<Marker> findByAuthor(String name);

    List<Marker> findByRegion(String region);

    List<Marker> findByType(String type);

    @Transactional
    List<Marker> findByAuthorAndType(String author, String type);
}

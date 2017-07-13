package com.movietheque.repositories;

import com.movietheque.entities.ActeurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActeurRepository extends JpaRepository<ActeurEntity, Long> {
    ActeurEntity findById(Long acteurId);
}


package com.ig5.iwa.avis.repository;

import com.ig5.iwa.avis.model.Avis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvisRepository extends JpaRepository<Avis, Long> {
    List<Avis> findByIdActivite(Long idActivite);
    List<Avis> findByUserId(Long userId);
}


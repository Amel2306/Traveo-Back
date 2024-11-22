package com.ig5.iwa.activite.repository;

import com.ig5.iwa.activite.model.Activite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActiviteRepository extends JpaRepository<Activite, Long> {
    List<Activite> findByUserId(Long userId);
    List<Activite> findByThemeId(Long themeId);
}


package com.ig5.iwa.themes.repository;

import com.ig5.iwa.themes.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
}


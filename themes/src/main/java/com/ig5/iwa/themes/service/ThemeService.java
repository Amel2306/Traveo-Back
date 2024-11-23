package com.ig5.iwa.themes.service;

import com.ig5.iwa.themes.model.Theme;
import com.ig5.iwa.themes.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    public Theme getThemeById(Long id) {
        return themeRepository.findById(id).orElseThrow(() -> new RuntimeException("Theme not found"));
    }

    public Theme createTheme(Theme theme) {
        return themeRepository.save(theme);
    }

    public Theme updateTheme(Long id, Theme updatedTheme) {
        Theme theme = getThemeById(id);
        theme.setLabel(updatedTheme.getLabel());
        theme.setDescription(updatedTheme.getDescription());
        theme.setImageDefault(updatedTheme.getImageDefault());
        return themeRepository.save(theme);
    }

    public boolean deleteTheme(Long id) {
        if (themeRepository.existsById(id)) {
            themeRepository.deleteById(id);
            return true; // La suppression a réussi
        }
        return false; // L'ID n'existe pas
    }
}



package com.ig5.iwa.themes.controller;

import com.ig5.iwa.themes.model.Theme;
import com.ig5.iwa.themes.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/themes")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @GetMapping
    public ResponseEntity<List<Theme>> getAllThemes() {
        return ResponseEntity.ok(themeService.getAllThemes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theme> getThemeById(@PathVariable("id") Long id) {
        // Appellez un service ou renvoyez une réponse
        Theme theme = themeService.getThemeById(id);
        return ResponseEntity.ok(theme);
    }

    @PostMapping
    public ResponseEntity<Theme> createTheme(@RequestBody Theme theme) {
        return ResponseEntity.ok(themeService.createTheme(theme));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Theme> updateTheme(@PathVariable Long id, @RequestBody Theme updatedTheme) {
        return ResponseEntity.ok(themeService.updateTheme(id, updatedTheme));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTheme(@PathVariable Long id) {
        boolean deleted = themeService.deleteTheme(id);
        if (!deleted) {
            return ResponseEntity.notFound().build(); // Si l'ID n'existe pas
        }
        return ResponseEntity.ok("Theme deleted successfully");
    }
}



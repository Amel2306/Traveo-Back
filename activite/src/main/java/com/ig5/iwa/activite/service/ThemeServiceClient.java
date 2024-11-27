package com.ig5.iwa.activite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ThemeServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final String THEME_SERVICE_URL = "http://162.38.32.231:8087/api/themes/";

    public boolean validateTheme(Long themeId) {
        try {
            System.out.println("Validating theme with ID: " + themeId);
            System.out.println("Validating url  " + THEME_SERVICE_URL + themeId);
            ResponseEntity<Void> response = restTemplate.getForEntity(THEME_SERVICE_URL + themeId, Void.class);
            System.out.println("Validation successful, response code: " + response.getStatusCode());
            return true;
        } catch (Exception e) {
            System.out.println("Validation failed for theme ID: " + themeId + ". Error: " + e.getMessage());
            return false;
        }
    }
}

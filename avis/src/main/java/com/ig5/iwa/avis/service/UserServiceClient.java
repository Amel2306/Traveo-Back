package com.ig5.iwa.avis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final String USER_SERVICE_URL = "http://162.38.32.231:8080/api/users/";

    public boolean validateUser(Long userId) {
        try {
            System.out.println("Validating user with ID: " + userId);
            ResponseEntity<Void> response = restTemplate.getForEntity(USER_SERVICE_URL + userId, Void.class);
            System.out.println("Validation successful, response code: " + response.getStatusCode());
            return true;
        } catch (Exception e) {
            System.out.println("Validation failed for user ID: " + userId + ". Error: " + e.getMessage());
            return false;
        }
    }
}

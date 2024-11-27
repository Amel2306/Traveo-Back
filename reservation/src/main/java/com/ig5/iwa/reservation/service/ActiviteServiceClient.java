package com.ig5.iwa.reservation.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ActiviteServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final String ACTIVITE_SERVICE_URL = "http://162.38.32.231:8086/api/activities/";

    public boolean validateActivite(Long activiteId) {
        try {
            System.out.println("Validating activite with ID: " + activiteId);
            ResponseEntity<Void> response = restTemplate.getForEntity(ACTIVITE_SERVICE_URL + activiteId, Void.class);
            System.out.println("Validation successful, response code: " + response.getStatusCode());
            return true;
        } catch (Exception e) {
            System.out.println("Validation failed for activite ID: " + activiteId + ". Error: " + e.getMessage());
            return false;
        }
    }
}

package com.ig5.iwa.avis.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.List;

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

    public List<Map<String, Object>> getActivitesByUserId(Long userId) {
            // Construire l'URL pour l'appel au microservice Activite
            final String url = ACTIVITE_SERVICE_URL + "/user/" + userId;

            // Effectuer la requête HTTP GET et récupérer la réponse
            ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Map<String, Object>>>() {}
            );
            return response.getBody();
    }

}

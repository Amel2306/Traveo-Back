package com.ig5.iwa.activite.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    // URL de base du microservice utilisateur (ajustez selon votre configuration réseau ou API Gateway)
    private static final String USER_SERVICE_URL = "http://localhost:8080/api/user/";

    /**
     * Vérifie si un utilisateur existe dans le microservice utilisateur.
     *
     * @param userId L'identifiant de l'utilisateur à valider.
     * @return true si l'utilisateur existe, false sinon.
     */
    public boolean validateUser(Long userId) {
        try {
            restTemplate.getForEntity(USER_SERVICE_URL + userId, Void.class); // Appel HTTP
            return true;
        } catch (Exception e) {
            return false; // Retourne false si l'utilisateur n'existe pas ou en cas d'erreur.
        }
    }
}


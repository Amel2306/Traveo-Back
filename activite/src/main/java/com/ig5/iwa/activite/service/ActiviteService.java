package com.ig5.iwa.activite.service;

import com.ig5.iwa.activite.model.Activite;
import com.ig5.iwa.activite.repository.ActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiviteService {

    @Autowired
    private ActiviteRepository activiteRepository;

    @Autowired
    private UserServiceClient userServiceClient; // Client pour le microservice utilisateur

    // Create an activite
    public Activite createActivite(Activite activity) {
        if (activity.getUserId() == null) {
            System.out.println(activity.getNomActivite());
            throw new RuntimeException("User ID must not be null.");
        }

        if (!userServiceClient.validateUser(activity.getUserId())) {
            throw new RuntimeException("User not found with ID: " + activity.getUserId());
        }
        return activiteRepository.save(activity);
    }

    // Read: Get all activities
    public List<Activite> getAllActivities() {
        return activiteRepository.findAll();
    }

    // Read: Get activities by user ID
    public List<Activite> getActivitiesByUserId(Long userId) {
        return activiteRepository.findByUserId(userId);
    }

    // Read: Get a single activite by ID
    public Activite getActiviteById(Long id) {
        return activiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activite not found with ID: " + id));
    }

    // Update an activite

    public Activite updateActivite(Long id, Activite updatedActivite) {
        // Récupérer l'activité existante ou lancer une exception
        Activite existingActivite = activiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activité introuvable avec l'ID : " + id));

        // Vérifier et mettre à jour les champs non nuls
        if (updatedActivite.getNomActivite() != null) {
            existingActivite.setNomActivite(updatedActivite.getNomActivite());
        }
        if (updatedActivite.getDescription() != null) {
            existingActivite.setDescription(updatedActivite.getDescription());
        }
        if (updatedActivite.getLatitude() != null) {
            existingActivite.setLatitude(updatedActivite.getLatitude());
        }
        if (updatedActivite.getLongitude() != null) {
            existingActivite.setLongitude(updatedActivite.getLongitude());
        }
        if (updatedActivite.getDateDebut() != null) {
            existingActivite.setDateDebut(updatedActivite.getDateDebut());
        }
        if (updatedActivite.getDateFin() != null) {
            existingActivite.setDateFin(updatedActivite.getDateFin());
        }
        if (updatedActivite.getPrix() != null) {
            existingActivite.setPrix(updatedActivite.getPrix());
        }
        if (updatedActivite.getNbPlaces() != null) {
            existingActivite.setNbPlaces(updatedActivite.getNbPlaces());
        }
        if (updatedActivite.getTags() != null) {
            existingActivite.setTags(updatedActivite.getTags());
        }
        if (updatedActivite.getImage() != null) {
            existingActivite.setImage(updatedActivite.getImage());
        }

        // Sauvegarder les modifications
        return activiteRepository.save(existingActivite);
    }


    public void deleteActivite(Long id) {
        activiteRepository.deleteById(id);
    }
}


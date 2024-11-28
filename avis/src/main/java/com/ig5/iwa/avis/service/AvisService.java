package com.ig5.iwa.avis.service;

import com.ig5.iwa.avis.model.Avis;
import com.ig5.iwa.avis.repository.AvisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AvisService {

    @Autowired
    private AvisRepository avisRepository;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private ActiviteServiceClient activiteServiceClient;


    public List<Avis> getAllAvis() {
        return avisRepository.findAll();
    }

    public Optional<Avis> getAvisById(Long id) {
        return avisRepository.findById(id);
    }

    public List<Avis> getAvisByActiviteId(Long idActivite) {
        return avisRepository.findByIdActivite(idActivite);
    }

    public List<Avis> getAvisByUserId(Long userId) {
        return avisRepository.findByUserId(userId);
    }

    public Avis createAvis(Avis avis) {
        if (!userServiceClient.validateUser(avis.getUserId())) {
            throw new RuntimeException("User not found with ID: " + avis.getUserId());
        }
        else if (!activiteServiceClient.validateActivite(avis.getIdActivite())) {
            throw new RuntimeException("Activite not found with ID: " + avis.getIdActivite());
        }
        return avisRepository.save(avis);
    }

    public Avis updateAvis(Long id, Avis updatedAvis) {
        return avisRepository.findById(id).map(existingAvis -> {
            existingAvis.setNote(updatedAvis.getNote());
            existingAvis.setCommentaire(updatedAvis.getCommentaire());
            return avisRepository.save(existingAvis);
        }).orElseThrow(() -> new RuntimeException("Avis not found with ID: " + id));
    }

    public void deleteAvis(Long id) {
        avisRepository.deleteById(id);
    }

    public List<Avis> getAvisOfUser(Long userId) {
        List<Avis> allAvis = new ArrayList<>();

        // Récupérer toutes les activités de l'utilisateur
        List<Map<String, Object>> activites = activiteServiceClient.getActivitesByUserId(userId);

        // Vérifier si l'utilisateur a des activités
        if (activites.isEmpty()) {
            System.out.println("No activities found for user ID: " + userId);
            return allAvis; // Retourne une liste vide
        }

        // Parcourir les activités
        for (Map<String, Object> activite : activites) {
            Long idActivite = ((Number) activite.get("idActivite")).longValue();

            // Récupérer les avis pour chaque activité
            List<Avis> avisList = avisRepository.findByIdActivite(idActivite);

            // Ajouter tous les avis dans la liste principale
            allAvis.addAll(avisList);
        }

        return allAvis;
    }
}

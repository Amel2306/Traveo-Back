package com.ig5.iwa.avis.service;

import com.ig5.iwa.avis.model.Avis;
import com.ig5.iwa.avis.repository.AvisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.HashMap;

import java.util.List;
import java.util.Optional;

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

    public Map<Long, List<Avis>> getAvisOfUser(Long userId) {
        Map<Long, List<Avis>> result = new HashMap<>();

        // Récupérer toutes les activités de l'utilisateur
        List<Map<String, Object>> activites = activiteServiceClient.getActivitesByUserId(userId);

        // Vérifier si l'utilisateur a des activités
        if (activites.isEmpty()) {
            System.out.println("No activities found for user ID: " + userId);
            return result; // Retourne un HashMap vide
        }

        // Parcourir les activités
        for (Map<String, Object> activite : activites) {
            Long idActivite = ((Number) activite.get("idActivite")).longValue();

            // Récupérer les avis pour chaque activité
            List<Avis> avisList = avisRepository.findByIdActivite(idActivite);

            // Ajouter à la map (activité -> liste d'avis)
            result.put(idActivite, avisList);
        }

        return result;
    }
}

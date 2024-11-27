package com.ig5.iwa.avis.service;

import com.ig5.iwa.avis.model.Avis;
import com.ig5.iwa.avis.repository.AvisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvisService {

    @Autowired
    private AvisRepository avisRepository;

    @Autowired
    private UserServiceClient userServiceClient;


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
}

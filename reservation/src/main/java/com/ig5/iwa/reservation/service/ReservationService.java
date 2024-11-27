package com.ig5.iwa.reservation.service;

import com.ig5.iwa.reservation.model.Reservation;
import com.ig5.iwa.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private ActiviteServiceClient activiteServiceClient;

    // Base URLs pour les microservices
    private static final String USER_SERVICE_URL = "http://localhost:8082/api/users/";
    private static final String ACTIVITY_SERVICE_URL = "http://localhost:8081/api/activities/";

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    /**public Reservation saveReservation(Reservation reservation) {
        // Vérification via RestTemplate avant d'enregistrer
        validateUser(reservation.getUtilisateurId());
        validateActivity(reservation.getActiviteId());
        return reservationRepository.save(reservation);
    }**/
    public Reservation saveReservation(Reservation reservation) {
        if (!userServiceClient.validateUser(reservation.getUtilisateurId())) {
            throw new RuntimeException("User not found with ID: " + reservation.getUtilisateurId());
        }
        else if (!activiteServiceClient.validateActivite(reservation.getActiviteId())) {
            throw new RuntimeException("Activite not found with ID: " + reservation.getActiviteId());
        }
        return reservationRepository.save(reservation);
    }


    public List<Reservation> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUtilisateurId(userId);
    }

    public List<Reservation> getReservationsByActivityId(Long activityId) {
        return reservationRepository.findByActiviteId(activityId);
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        // Récupérer l'entité existante
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée pour id : " + id));

        // Validation via RestTemplate
        //validateUser(updatedReservation.getUtilisateurId());
        //validateActivity(updatedReservation.getActiviteId());

        // Mettre à jour les champs nécessaires
        reservation.setActiviteId(updatedReservation.getActiviteId());
        reservation.setUtilisateurId(updatedReservation.getUtilisateurId());
        reservation.setDateReservation(updatedReservation.getDateReservation());

        // Sauvegarder l'entité
        reservationRepository.save(reservation);

        // Retourner l'entité mise à jour
        return reservation;
    }



    public void deleteReservation(Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Réservation non trouvée pour id : " + id);
        }
    }

    // Méthodes utilisant RestTemplate
    //private void validateUser(Long userId) {
        //String url = USER_SERVICE_URL + userId;
        //try {
            //restTemplate.getForObject(url, Object.class);
        //} catch (Exception e) {
            //throw new RuntimeException("Utilisateur avec id " + userId + " non trouvé.");
        //}
    //}

    private void validateUser(Long userId) {
        // Simulation de validation toujours réussie
        System.out.println("Validation simulée de l'utilisateur avec ID : " + userId);
    }


    private void validateActivity(Long activityId) {
        String url = ACTIVITY_SERVICE_URL + activityId;
        try {
            restTemplate.getForObject(url, Object.class);
        } catch (Exception e) {
            throw new RuntimeException("Activité avec id " + activityId + " non trouvée.");
        }
    }
}

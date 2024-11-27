package com.ig5.iwa.reservation.controller;

import com.ig5.iwa.reservation.model.Reservation;
import com.ig5.iwa.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // Récupérer toutes les réservations
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    // Créer une réservation
    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.saveReservation(reservation);
    }

    // Récupérer toutes les réservations par userID
    @GetMapping("/user/{userId}")
    public List<Reservation> getAllReservationsByUserId(@PathVariable("userId") Long userId) {
        return reservationService.getReservationsByUserId(userId);
    }

    // Récupérer toutes les réservations par activityID
    @GetMapping("/activity/{activityId}")
    public List<Reservation> getAllReservationsByActivityId(@PathVariable("activityId") Long activityId) {
        return reservationService.getReservationsByActivityId(activityId);
    }

    // Récupérer une réservation par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Long id) {
        Optional<Reservation> reservation = reservationService.getReservationById(id);
        if (reservation.isPresent()) {
            return ResponseEntity.ok(reservation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Mettre à jour une réservation
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable("id") Long id, @RequestBody Reservation updatedReservation) {
        try {
            Reservation updated = reservationService.updateReservation(id, updatedReservation);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer une réservation
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable("id") Long id) {
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.ok("Réservation supprimée avec succès.");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.ig5.iwa.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ig5.iwa.reservation.model.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUtilisateurId(Long userId);
    List<Reservation> findByActiviteId(Long activityId);
}


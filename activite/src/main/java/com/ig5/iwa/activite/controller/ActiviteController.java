package com.ig5.iwa.activite.controller;

import com.ig5.iwa.activite.service.ActiviteService;
import com.ig5.iwa.activite.model.Activite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActiviteController {

    @Autowired
    private ActiviteService activiteService;

    // Create
    @PostMapping("/")
    public ResponseEntity<Activite> createActivite(@RequestBody Activite activite) {
        return ResponseEntity.ok(activiteService.createActivite(activite));
    }

    // Read all
    @GetMapping("/")
    public ResponseEntity<List<Activite>> getAllActivities() {
        return ResponseEntity.ok(activiteService.getAllActivities());
    }

    // Read by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Activite>> getActivitiesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(activiteService.getActivitiesByUserId(userId));
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<Activite> getActiviteById(@PathVariable Long id) {
        return ResponseEntity.ok(activiteService.getActiviteById(id));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Activite> updateActivite(@PathVariable Long id, @RequestBody Activite updatedActivite) {
        return ResponseEntity.ok(activiteService.updateActivite(id, updatedActivite));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteActivite(@PathVariable Long id) {
        activiteService.deleteActivite(id);
        return ResponseEntity.ok("Activite deleted successfully");
    }
}
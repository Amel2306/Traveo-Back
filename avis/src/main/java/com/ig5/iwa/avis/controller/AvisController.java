package com.ig5.iwa.avis.controller;

import com.ig5.iwa.avis.model.Avis;
import com.ig5.iwa.avis.service.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avis")
public class AvisController {

    @Autowired
    private AvisService avisService;

    @GetMapping
    public ResponseEntity<List<Avis>> getAllAvis() {
        return ResponseEntity.ok(avisService.getAllAvis());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avis> getAvisById(@PathVariable Long id) {
        return avisService.getAvisById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/activite/{idActivite}")
    public ResponseEntity<List<Avis>> getAvisByActiviteId(@PathVariable Long idActivite) {
        return ResponseEntity.ok(avisService.getAvisByActiviteId(idActivite));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Avis>> getAvisByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(avisService.getAvisByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Avis> createAvis(@RequestBody Avis avis) {
        return ResponseEntity.ok(avisService.createAvis(avis));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avis> updateAvis(@PathVariable Long id, @RequestBody Avis avis) {
        return ResponseEntity.ok(avisService.updateAvis(id, avis));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAvis(@PathVariable Long id) {
        avisService.deleteAvis(id);
        return ResponseEntity.ok("Avis deleted successfully");
    }
}

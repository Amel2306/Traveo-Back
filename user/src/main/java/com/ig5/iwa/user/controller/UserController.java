package com.ig5.iwa.user.controller;

import com.ig5.iwa.user.model.User;
import com.ig5.iwa.user.service.UserService;
import com.ig5.iwa.user.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();

            // Vérifier si la liste des utilisateurs est vide
            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No users found.");
            }

            // Retourner la liste des utilisateurs
            return ResponseEntity.ok(users);
        } catch (Exception ex) {
            // Gérer une exception inattendue
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while retrieving users: " + ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userService.getUserById(id);
        System.out.println("Received request for user ID: " + id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Enregistrement d'un utilisateur
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        // Enregistre l'utilisateur et récupère l'entité persistée
        User savedUser = userService.saveUser(user);

        // Génère le token
        String token = jwtUtil.generateToken(savedUser.getEmail());

        // Retourne une réponse structurée
        return ResponseEntity.ok(Map.of(
                "message", "register successful",
                "token", token,
                "id", savedUser.getUserId() // Utilise l'ID de l'utilisateur persisté
        ));
    }

    // Connexion d'un utilisateur
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null && userService.getPasswordEncoder().matches(user.getPassword(), existingUser.getPassword())) {
            String token = jwtUtil.generateToken(existingUser.getEmail());
            return ResponseEntity.ok(Map.of(
                    "message", "Login successful",
                    "email", existingUser.getEmail(),
                    "username", existingUser.getUsername(),
                    "role", existingUser.getRole(),
                    "token", token,
                    "id", existingUser.getUserId()
            ));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        try {
            User updated = userService.updateUser(id, updatedUser);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

}

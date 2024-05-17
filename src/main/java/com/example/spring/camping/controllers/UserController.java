package com.example.spring.camping.controllers;


import java.util.*;

import com.example.spring.camping.models.ManageUsers.DetailsUser;
import com.example.spring.camping.servicesImpl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import com.example.spring.camping.models.ManageUsers.User;
import com.example.spring.camping.respositories.UserRepository;


@RequestMapping("/utilisateurs")
@CrossOrigin("*")
@RestController
public class UserController {

    @Autowired
    private UserRepository utilRepo;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    PasswordEncoder encoder;


    @GetMapping("/Consulter")
    public List<User> ListerUtilisateurs() {
        return userServiceImpl.getAll();
    }

    @GetMapping("Rechercher/{id}")
    public User ConsulterUtilisateur(@PathVariable Long id) {
        return userServiceImpl.getById(id);
    }

    @PutMapping("/Modifier")
    public User ModifierUtilisateur(@RequestBody User user) {
        return userServiceImpl.update(user);
    }

    @PutMapping("Supprimer/{id}")
    public void SupprimerUtilisateur(@PathVariable Long id) {
        userServiceImpl.delete(id);
    }

    @PutMapping("/AffecterDetailsUser/{id}")
    public  void AffecterDetailsUser (@PathVariable Long id , @RequestBody DetailsUser detailsUser){
        userServiceImpl.AffecterDetailsUser(id,detailsUser);
    }
    @GetMapping("/ChangePassword/{Id}/{AncienPassword}/{NewPassword}")
    public ResponseEntity<Map<String, String>> changerMotDePasse(@PathVariable Long Id, @PathVariable String AncienPassword, @PathVariable String NewPassword) {
        Map<String, String> response = new HashMap<>();
        if (userServiceImpl.changerMotDePasse(Id, AncienPassword, NewPassword)) {
            response.put("message", "Mot de passe changé avec succès.");
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "Ancien mot de passe incorrect ou utilisateur non trouvé.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/generateNewPassword/{email}")
    public ResponseEntity<Map<String, String>> generateAndSendNewPassword(@PathVariable String email) {
        try {
            userServiceImpl.generateAndSendNewPassword(email);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Nouveau mot de passe généré et envoyé avec succès.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @GetMapping("/ExistUser/{email}")
    public Optional<User> existUserByMail (@PathVariable String email)
    {
        return  userServiceImpl.existUser(email);
    }


}

package com.example.spring.camping.servicesImpl.ReclamationServicesImpl;

import com.example.spring.camping.models.ManageUsers.User;
import com.example.spring.camping.models.Reclamation.Reclamation;
import com.example.spring.camping.models.Reclamation.SuiviReclamation;

import com.example.spring.camping.respositories.ReclamationRepository.ReclamationRepository;
import com.example.spring.camping.respositories.ReclamationRepository.SuiviReclamationRepository;
import com.example.spring.camping.respositories.UserRepository;
import com.example.spring.camping.services.ReclamationServices.SuiviReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuiviReclamationServiceImpl implements SuiviReclamationService {

    @Autowired
    private SuiviReclamationRepository suiviReclamationRepository;

    @Autowired
    private ReclamationRepository reclamationRepository;

    @Autowired
    private UserRepository userRepository; // Properly inject UserRepository

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public SuiviReclamation saveSuiviReclamation(SuiviReclamation suiviReclamation) {
        SuiviReclamation savedSuiviReclamation = suiviReclamationRepository.save(suiviReclamation);
        sendEmailNotification(savedSuiviReclamation);
        return savedSuiviReclamation;
    }

    @Override
    public SuiviReclamation updateSuiviReclamation(SuiviReclamation suiviReclamation) {
        return suiviReclamationRepository.save(suiviReclamation);
    }

    @Override
    public void deleteSuiviReclamation(long id) {
        suiviReclamationRepository.deleteById(id);
    }

    @Override
    public SuiviReclamation getSuiviReclamationById(long id) {
        Optional<SuiviReclamation> optionalSuiviReclamation = suiviReclamationRepository.findById(id);
        return optionalSuiviReclamation.orElse(null);
    }

    @Override
    public List<SuiviReclamation> getAllSuiviReclamations() {
        return suiviReclamationRepository.findAll();
    }

    private void sendEmailNotification(SuiviReclamation suiviReclamation) {
        Reclamation reclamation = reclamationRepository.getById(suiviReclamation.getIdReclamation());
        Long idUser = reclamation.getIdClient();

        User user = userRepository.getById(idUser);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("your-email@example.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Your reclamation has a new reply");
        mail.setText("Dear " + user.getNom() + ",\n\n" +
                "Your reclamation titled '" + reclamation.getTitle() + "' has received a new reply.\n\n" +
                "Reply: " + suiviReclamation.getDescSuivi() + "\n\n" +
                "Best regards,\n" +
                "Your Support Team");

        javaMailSender.send(mail);
    }
}

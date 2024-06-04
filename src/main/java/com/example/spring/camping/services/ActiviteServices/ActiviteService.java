package com.example.spring.camping.services.ActiviteServices;

import com.example.spring.camping.models.Activites.Activite;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ActiviteService<Activite> {
        Activite ajoutActivite(Activite a);
        Activite updateActivite(Activite a);
        Activite updateActiviteById(long id, Activite activite);
        List<Activite> retrieveAll();
        void delete(long id);
        // Activite affecteurCamperToActivite();
        Optional<Activite> retrieveActiviteById(long id);



        Activite approuverActivite (long ActiviteID);
        Activite desapprouverActivite (long ActiviteID);
        Activite participerActivite (Activite a);
        List<Activite> rechercheActivites (String critere );
        Activite annulerActivite (Activite a);
       // Activite trouverActiviteAvecPlusParticipants();

        List<Float> getPrixActiviteByMonth();
}

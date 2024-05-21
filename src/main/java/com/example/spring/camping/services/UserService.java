package com.example.spring.camping.services;

import java.util.List;
import java.util.Optional;

import com.example.spring.camping.models.ManageUsers.DetailsUser;
import com.example.spring.camping.models.ManageUsers.User;

public interface UserService {
 
	public  User AjouterUtilisateur(  User  U  ); 
	public List< User> ConsulterUtilisateurs () ; 
	public  User ConsulterUtilById (Long id); 
	public void SupprimeUtilById (Long id); 
	public  User ModifierProfil (  User U  );
	public List< User> ListeParRole (String RoleNom);
	public List<User> RechercherUtilisateur ( String critere);
	public List<User> ConsulterLesCampeurs();
	public List<DetailsUser> ConsulterCampeursDeMemeCamp ();
	public void StatistiquesPreferencesUser (List <DetailsUser> detailsUsers);

	public void RecommenderCampings( DetailsUser detailsUser);
	public void RecommenderCampingsParEmail( DetailsUser detailsUser);
	public  void RecommenderActivities( DetailsUser detailsUser);
	public void ConsulterRecommendations ( );

	public void AjouterCampingListeFavoris();

	public  void SupprimerCampingListeFavoris();








	
}

package com.example.spring.camping.servicesImpl;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import com.example.spring.camping.models.ERole;
import com.example.spring.camping.models.ManageUsers.*;
import com.example.spring.camping.respositories.DetailsUserRepository;
import com.example.spring.camping.respositories.RoleRepository;
import com.example.spring.camping.services.ICrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.spring.camping.respositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service 

public class UserServiceImpl implements ICrud<User>  {
	
	@Autowired
	private UserRepository utilRepo ;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private DetailsUserRepository detailsUserRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private JavaMailSender javaMailSender;


	public ResponseEntity<?> addNewUser (@Valid @RequestBody AddUserRequest addUserRequest )
	{

		if (utilRepo.existsByEmail(addUserRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Email is already taken ! ")) ;
		}

		// add new user
		User user = new User (addUserRequest.getFirstName() , addUserRequest.getLastName() ,
				addUserRequest.getEmail() , addUserRequest.getAdresse(), addUserRequest.getDate_naissance(),addUserRequest.getTelephone(),encoder.encode(addUserRequest.getPassword()));

		String strRole="";
		strRole = addUserRequest.getRole();


		// affecter un role à l'utilisateur
		if (strRole == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Role non connu  ! ")) ;
		}
		else {
			switch (strRole) {
				case "CAMPEUR":

					Role RoleCAMPEUR = roleRepository.findByRole(ERole.CAMPEUR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					user.setRole(RoleCAMPEUR);


					System.out.println("here role CAMPEUR " + strRole);
					break;
				case "CENTRECAMPING":

					Role RoleCENTRECAMPING = roleRepository.findByRole(ERole.CENTRECAMPING)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					user.setRole(RoleCENTRECAMPING);


					System.out.println("here role CENTRECAMPEUR " + strRole);
					break;
				case "ADMIN":

					Role RoleADMIN = roleRepository.findByRole(ERole.ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					user.setRole(RoleADMIN);
					break;



			}
		}

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("geeksjava44@gmail.com");
		mail.setTo(addUserRequest.getEmail());
		mail.setSubject("Bienvenue");
		mail.setText("Cher(e) "+addUserRequest.getLastName()+",\n\n"
				+ "Vous êtes un nouveau utilisateur chez notre platform de camping \n\n"

				+ "Veuillez vous connecter à votre compte en utilisant votre mot de passe . \n\n"
				+ "Si vous avez des questions ou besoin d'assistance, n'hésitez pas à nous contacter.\n\n"

				+ "Cordialement,\n"
				+ "Notre équipe");
		javaMailSender.send(mail);
		user.setStatus(true);
		utilRepo.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

	}
	public List<User> Rechercher(String RoleNom ,String recherche ) {
		
		return utilRepo.RechercherAdherent(RoleNom ,recherche);
	}


	public Optional<User> FindUserByMail(String email) {
		
		return utilRepo.findByEmail(email);
	}

	public Optional<User> FindUserByToken(String token) {
		
		return utilRepo.findByResetToken(token);
	}


	@Override
	public List<User> getAll() {
		return utilRepo.findAll();
	}

	@Override
	public User getById(long id) {
		Optional<User> m = utilRepo.findById(id) ;
		return m.isPresent() ? m.get() : null ;
	}

	@Override
	public User add(User user) {
		return null;
	}


	@Override
	public void delete(long id) {
		Optional<User> u = utilRepo.findById(id);
		u.get().setStatus(false);
		utilRepo.save(u.get());
		DetailsUser detailsUser = u.get().getDetailsUser();
		detailsUser.setStatus(false);
		detailsUserRepository.save(detailsUser);

	}

	@Override
	public User update(User user) {
		User u = utilRepo.getById(user.getId());
		u.setNom(user.getNom());
		u.setPrenom(user.getPrenom());
		u.setAdresse(user.getAdresse());
		u.setTelephone(user.getTelephone());


		return utilRepo.save(u);
	}

	public void AffecterDetailsUser (long idUser , DetailsUser detailsUser)
	{
		User u = utilRepo.getById(idUser);
		u.setDetailsUser(detailsUser);
		utilRepo.save(u);
	}
	public boolean changerMotDePasse(Long Id, String AncienPassword, String NewPassword) {
		Optional<User> userOptional = utilRepo.findById(Id);

		if (userOptional.isPresent()) {
			User user = userOptional.get();

			// Vérifiez si l'ancien mot de passe correspond
			if (encoder.matches(AncienPassword, user.getPassword())) {
				// Encodez et mettez à jour le nouveau mot de passe
				user.setPassword(encoder.encode(NewPassword));
				utilRepo.save(user);
				return true;
			}
		}
		return false;
	}

	public static String generatePassword(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(characters.length());
			sb.append(characters.charAt(randomIndex));
		}

		return sb.toString();
	}
	public void generateAndSendNewPassword(String email) {
		Optional<User> userOptional = utilRepo.findByEmail(email);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			String newPassword = generatePassword(18);



			user.setPassword(encoder.encode(newPassword));
			utilRepo.save(user);

			// Envoyer le nouveau mot de passe par e-mail
			sendPasswordByEmail(user.getEmail(), newPassword);
		} else {
			throw new RuntimeException("Utilisateur non trouvé avec l'email : " + email);
		}
	}
	public void sendPasswordByEmail(String email, String newPassword) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("geeksjava44@gmail.com");
		mail.setTo(email);
		mail.setSubject("Nouveau mot de passe");
		mail.setText("Cher utilisateur,\n\n"
				+ "Voici votre nouveau mot de passe : " + newPassword + "\n\n"
				+ "Veuillez vous connecter à votre compte en utilisant ce mot de passe.\n\n"
				+ "Si vous avez des questions ou besoin d'assistance, n'hésitez pas à nous contacter.\n\n"
				+ "Cordialement,\n"
				+ "L'équipe de Camping");
		javaMailSender.send(mail);
	}

	public Optional<User> existUser (String email)
	{
		return utilRepo.findByEmail(email);
	}


}


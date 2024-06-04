package com.example.spring.camping.models.ManageUsers;

import com.example.spring.camping.models.CampLocations.Photo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse implements Serializable {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String firstName;
	private String lastName ; 
	private String email;
	private Long telephone;
	private String adresse ;

	private Date date_naissance;


	private List<DetailsUser> detailsUser;

	private String role ;
	private Photo photo;




    public JwtResponse(String token, Long id, String firstName, String lastName, String email, Long telephone, String adresse, Date dateNaissance,   List<DetailsUser> detailsUser, String role , Photo photo) {
		super();
		this.token = token;
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telephone=telephone;
		this.adresse=adresse;
		this.date_naissance=dateNaissance;

		this.detailsUser=detailsUser;

		this.role = role;
		this.photo=photo;
    }


}
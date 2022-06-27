package com.kodlamaio.rentACar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public abstract class User {
	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	/*@Column(name = "user_first_name")
	private String userFirstName;
	
	@Column(name="user_last_name")
	private String userLastName;
	
	@Column(name="user_national_identity")
	private String nationalIdentity;
	
	@Column(name="birth_year")
	private Integer birthYear;*/
	
	@Column(name="user_password")
	private String userPassword;
	
	@Column(name ="user_email")
	private String userEmail; 
	
	/*@OneToMany (mappedBy = "user")
	private List<Rental> rentals;
	
	@OneToMany(mappedBy="user")
	private List<Adress> adresses;*/
	
	
}

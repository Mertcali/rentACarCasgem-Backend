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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "customer_id",referencedColumnName = "id")
@Table(name="customers")
public class Customer extends User {

	@Column(name = "customer_id", insertable =false, updatable=false)
	private int customerId;
	
	@Column(name ="customer_number")
	private String customerNumber;
	
	@Column(name="findex_score")
	private Integer findexScore;
	
	@OneToMany(mappedBy="customer")
	private List<Adress> adresses;
	
	@OneToMany(mappedBy="customer")
	private List<Rental> rentals;
	
}

package com.kodlamaio.rentACar.entities.concretes;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "cars","cities","additionals" })
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rentals")
public class Rental {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "pickup_date")
	private Date pickupDate;

	@Column(name = "return_date")
	private Date returnDate;

	@Column(name = "total_days")
	private int totalDaysRental;

	@Column(name = "total_price")
	private double totalPrice;

	@ManyToOne()	
	@JoinColumn(name = "car_id")
	private Car car;
	
	/*@ManyToOne
    @JoinColumn(name = "pick_up_city_id", referencedColumnName = "id")
    private City pickUpCity;

    @ManyToOne
    @JoinColumn(name = "return_city_id", referencedColumnName = "id")
    private City returnCity;*/
	
	@Column(name = "pick_up_city_id")
	private Integer pickupCityId;
	
	@Column(name = "return_city_id")
	private Integer returnCityId;
    


    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "individual_customer_id")
    private IndividualCustomer individualCustomer;
    
    @ManyToOne
    @JoinColumn(name = "corporate_customer_id")
    private CorporateCustomer corporateCustomer;
    
    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;
    
	@ManyToOne
	@JoinColumn(name = "ordered_additional_item_id")
	private OrderedAdditionalItem orderedAdditionalItem;
	
	@OneToMany(mappedBy = "rental") //, cascade = CascadeType.REMOVE --> içerdeki sistemi yapmak yerine sadece bu kullanılabilir
	private List<Invoice> invoices;
    
    
    /*@OneToMany(mappedBy = "rental")
    private List<RentalDetail> rentalDetails;*/
    
}

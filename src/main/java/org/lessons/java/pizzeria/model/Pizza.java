package org.lessons.java.pizzeria.model;

import java.util.List;

import org.hibernate.annotations.Formula;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "menu")
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Size(min=2, max=255)
	@NotEmpty
	private String name;
	
	@NotNull
	@NotEmpty
	private String description;
	
	@NotNull
	@NotEmpty
	private String picture;
	
	@NotNull
	@Column(name = "price", nullable = false)
	private Float price;
	
	//specifico la relazione one to many (una pizza può avere più offerte speciali)
	
	@OneToMany(mappedBy = "pizza", cascade = {CascadeType.REMOVE})
	private List<SpecialOffer> specialOffers;

	@Formula("(SELECT menu.price - (menu.price * special_offers.discount) "
			+ "from menu "
			+ "INNER JOIN special_offers on menu.id = pizza_id "
			+ "WHERE special_offers.pizza_id is not null)")
	private Float discountedPrice;


	// getters + setters
	public List<SpecialOffer> getSpecialOffers() {
		return specialOffers;
	}
	
	public void setSpecialOffers(List<SpecialOffer> specialOffers) {
		this.specialOffers = specialOffers;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Float getPrice() {
		
		
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
}

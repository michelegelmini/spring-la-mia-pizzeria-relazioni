package org.lessons.java.pizzeria.model;

import java.util.List;

import org.hibernate.annotations.Formula;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
			+ "INNER JOIN special_offers on menu.id = special_offers.pizza_id "
			+ "WHERE special_offers.pizza_id = id)")
	private Float discountedPrice;
	
	@Formula("(SELECT IFNULL(special_offers.discount, '0') "
			+ "from special_offers "
			+ "INNER JOIN menu on menu.id = special_offers.pizza_id "
			+ "Where menu.id = id)")
	private Float discountPercentage;

	
	
	//relation with ingredients many to many
	@ManyToMany()
	@JoinTable(
			name = "pizza_ingredient",
			joinColumns = @JoinColumn(name = "pizza_id"),
			inverseJoinColumns = @JoinColumn(name = "ingredient_id")
			)
	
	private List<Ingredient> ingredients;

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Float getPrice() {
		return price;
	}

	public Float getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Float discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

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

	public String getFormattedPrice() {
		
		
		return String.format("%.2f", price);
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(Float discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	
	public String getFormattedDiscountedPrice() {
				
		if(this.getDiscountedPrice() != null && this.getDiscountPercentage() != 1) {
			return "€ " + String.format("%.2f", this.getDiscountedPrice());
		} else if (this.getDiscountedPrice() != null && this.getDiscountPercentage() == 1){
			return "Free!";
		} else {
			return "";
	}
	}
	
	public String getFormattedDiscount() {
		
		Float discountToFormat = this.getDiscountPercentage();
		
		if(discountToFormat == null) {
			return "";
		} else {
			return "-" + String.format("%.0f", discountToFormat * 100)  + "%";
		}
		
	}
	
}

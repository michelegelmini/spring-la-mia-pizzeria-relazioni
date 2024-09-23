package org.lessons.java.pizzeria.repo;

import java.util.List;

import org.lessons.java.pizzeria.model.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Integer>{
	
	public List<SpecialOffer> findByOfferNameContainingIgnoreCaseOrderByIdAsc(String name);
	

}

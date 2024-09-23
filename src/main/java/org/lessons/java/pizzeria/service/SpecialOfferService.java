package org.lessons.java.pizzeria.service;

import java.util.List;

import org.lessons.java.pizzeria.model.Ingredient;
import org.lessons.java.pizzeria.model.SpecialOffer;
import org.lessons.java.pizzeria.repo.IngredientRepository;
import org.lessons.java.pizzeria.repo.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferService {

	@Autowired
	private SpecialOfferRepository repository;
	
	public List<SpecialOffer> findAllSortedById(){
		return repository.findAll(Sort.by("id"));
	}
	
	public SpecialOffer create(SpecialOffer specialOffer) {
		return repository.save(specialOffer);
	}
	
	public SpecialOffer update(SpecialOffer specialOffer) {
		return repository.save(specialOffer);
	}

	public SpecialOffer findById(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	
	
}

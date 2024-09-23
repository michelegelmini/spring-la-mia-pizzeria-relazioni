package org.lessons.java.pizzeria.service;

import java.util.List;

import org.lessons.java.pizzeria.model.Ingredient;
import org.lessons.java.pizzeria.repo.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepository repository;
	
	public List<Ingredient> findAllSortedById(){
		return repository.findAll(Sort.by("id"));
	}
	
	public Ingredient create(Ingredient ingredient) {
		return repository.save(ingredient);
	}
	
	public Ingredient update(Ingredient ingredient) {
		return repository.save(ingredient);
	}

	public Ingredient findById(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	
	
}

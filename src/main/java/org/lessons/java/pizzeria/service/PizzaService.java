package org.lessons.java.pizzeria.service;

import java.util.List;

import org.lessons.java.pizzeria.model.Ingredient;
import org.lessons.java.pizzeria.model.Pizza;
import org.lessons.java.pizzeria.repo.IngredientRepository;
import org.lessons.java.pizzeria.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepository repository;
	
	public List<Pizza> findAllSortedById(){
		return repository.findAll(Sort.by("id"));
	}
	
	public List<Pizza> findByName(String name){
	return repository.findByNameContainingIgnoreCaseOrderByIdAsc(name);
	
	}
	
	public Pizza create(Pizza pizza) {
		return repository.save(pizza);
	}
	
	public Pizza update(Pizza pizza) {
		return repository.save(pizza);
	}

	public Pizza findById(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	
	
}

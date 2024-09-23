package org.lessons.java.pizzeria.repo;

import java.util.List;

import org.lessons.java.pizzeria.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{
	
	public List<Ingredient> findByNameContainingIgnoreCaseOrderByIdAsc(String name);
	

}

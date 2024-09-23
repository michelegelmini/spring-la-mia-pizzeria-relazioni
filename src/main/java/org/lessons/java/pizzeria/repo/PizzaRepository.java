package org.lessons.java.pizzeria.repo;

import java.util.List;

import org.lessons.java.pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
	
	public List<Pizza> findByNameContainingIgnoreCaseOrderByIdAsc(String name);
	
	
}

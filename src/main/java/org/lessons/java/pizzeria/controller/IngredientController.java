package org.lessons.java.pizzeria.controller;

import org.lessons.java.pizzeria.model.Ingredient;
import org.lessons.java.pizzeria.model.Pizza;
import org.lessons.java.pizzeria.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	private IngredientService service;

	@GetMapping()
	public String index(Model model) {
		model.addAttribute("ingredients", service.findAllSortedById());
		return "/ingredients/index";
	}

	// create
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("ingredient", new Ingredient());
		return "/ingredients/create";
	}

	// Store
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("pizza") Ingredient formIngredient, BindingResult bindingResult,
			RedirectAttributes attributes, Model model) {

		if (bindingResult.hasErrors()) {
			return "/ingredients/create";
		}
		service.create(formIngredient);
		attributes.addFlashAttribute("successMessage", formIngredient.getName() + " has been created!");

		return "redirect:/ingredients";
	}
	
	//edit
		@GetMapping("/edit/{id}")
		public String edit(@PathVariable("id") Integer id, Model model) {
			
			//metodo lungo
//				//trovo la pizza
//				Pizza pizzaToEdit = repo.findById(id).get();
//				//la inserisco nel model
//				model.addAttribute("pizza", pizzaToEdit);
			
			//metodo rapido
			model.addAttribute("ingredient", service.findById(id));
			
			//restituisco la view con il model inserito
			return "ingredients/edit";
		}
		
		//update
		@PostMapping("/edit/{id}")
		public String update(@Valid @ModelAttribute("ingredient") Ingredient updatedFormIngredient, BindingResult bindingResult, RedirectAttributes attributes, Model model) {
			
			//se ci sono errori nel form, mostra gli errori
			if (bindingResult.hasErrors()) {
				return "/ingredients/edit";
			}
			//altrimenti salva la pizza nella repo
			service.update(updatedFormIngredient);	
			//ritorna al menu aggiornato
			attributes.addFlashAttribute("successMessage", updatedFormIngredient.getName() + " has been updated!");
			
			
			return "redirect:/ingredients";
		}
		
		//delete
		@PostMapping("/delete/{id}")
		public String delete(@PathVariable("id") Integer id, Ingredient ingredientToDelete, RedirectAttributes attributes) {
			
			Ingredient ingredient = service.findById(id);
			
			//deleteById cerca ed elimina in un unico comando
			ingredientToDelete = service.findById(id);
			
			for (Pizza pizza : ingredient.getPizzas()) {
				pizza.getIngredients().remove(ingredient);
			}
			
			attributes.addFlashAttribute("deletedMessage", ingredientToDelete.getName() + " has been deleted!");
			service.deleteById(id);
			
			return "redirect:/ingredients";
		}
	

}

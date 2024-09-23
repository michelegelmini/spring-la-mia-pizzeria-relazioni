package org.lessons.java.pizzeria.controller;

import org.lessons.java.pizzeria.model.SpecialOffer;

import org.lessons.java.pizzeria.service.SpecialOfferService;
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
@RequestMapping("/specialOffers")
public class SpecialOfferController {

	@Autowired
	private SpecialOfferService service;

	@GetMapping()
	public String index(Model model) {
		model.addAttribute("specialOffers", service.findAllSortedById());
		return "/specialOffers/index";
	}

	// create
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("specialOffer", new SpecialOffer());
		return "/specialOffers/create";
	}

	// Store
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer,
			BindingResult bindingResult, RedirectAttributes attributes, Model model) {

		if (bindingResult.hasErrors()) {
			return "/specialOffers/create";
		}
		service.create(formSpecialOffer);
		attributes.addFlashAttribute("successMessage", formSpecialOffer.getOfferName() + " has been created!");

		return "redirect:/pizzas/" + formSpecialOffer.getPizza().getId();
	}

	// edit
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {

		// metodo lungo
//				//trovo la pizza
//				Pizza pizzaToEdit = repo.findById(id).get();
//				//la inserisco nel model
//				model.addAttribute("pizza", pizzaToEdit);

		// metodo rapido
		model.addAttribute("specialOffer", service.findById(id));

		// restituisco la view con il model inserito
		return "specialOffers/edit";
	}

	// update
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("specialOffer") SpecialOffer updatedFormSpecialOffer,
			BindingResult bindingResult, RedirectAttributes attributes, Model model) {

		// se ci sono errori nel form, mostra gli errori
		if (bindingResult.hasErrors()) {
			return "/specialOffers/edit";
		}
		// altrimenti salva la pizza nella repo
		service.update(updatedFormSpecialOffer);
		// ritorna al menu aggiornato
		attributes.addFlashAttribute("successMessage", updatedFormSpecialOffer.getOfferName() + " has been updated!");

		return "redirect:/specialOffers";
	}

	// delete
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, SpecialOffer specialOfferToDelete,
			RedirectAttributes attributes) {

		// deleteById cerca ed elimina in un unico comando
		specialOfferToDelete = service.findById(id);

		attributes.addFlashAttribute("deletedMessage", specialOfferToDelete.getOfferName() + " has been deleted!");
		service.deleteById(id);

		return "redirect:/specialOffers";
	}

}

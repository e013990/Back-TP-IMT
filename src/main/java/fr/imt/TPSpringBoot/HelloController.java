package fr.imt.TPSpringBoot;

import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

	@Autowired
	AnnuaireItf anu;

	@GetMapping("/entree")
	public ResponseEntity<?> getAll()
	{
		return ResponseEntity.status(HttpStatus.OK).body(anu.getAll());
	}
	
	@GetMapping("/entree/{id}")
	public ResponseEntity<?> getOne(@PathVariable int id)
	{
		if(anu.getfromId(id)==null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune personne avec cet ID");
		}
		else
		{
		return ResponseEntity.status(HttpStatus.OK).body(anu.getfromId(id));
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id)
	{
		if(anu.getfromId(id)==null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune personne avec cet ID");
		}
		else
		{
		anu.delPerson(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	
	@GetMapping("/annuaire/ajouter")
	public String retourneAjouter()
	{
		return "annuaire/ajouter";
	}
	
	@GetMapping("/annuaire/del/{id}")
	public String delete(Model model, @PathVariable int id)
	{
		anu.delPerson(id);
		model.addAttribute("listperson",anu.getAll());
		return "redirect:/annuaire/recherche";
	}
	
	@GetMapping("/annuaire/modifier/{id}")
	public String modifier(Model model, @PathVariable int id)
	{
		Person p=anu.getfromId(id);
		model.addAttribute("person",p);
		return "annuaire/modifier";
	}
	
	@PostMapping("/annuaire/modifier") 
	public String add(@RequestParam int id,@RequestParam String name, @RequestParam String surname,@RequestParam String phone,@RequestParam String city)
	{
		Person p1=new Person(id,name,surname,phone,city);
		anu.addPerson(p1);
		return "redirect:/annuaire/recherche";
	}
	
	@PostMapping("/entree") 
	public ResponseEntity<?> add(@RequestBody Person newP)
	{
		if (anu.getfromId(newP.getId())!=null)
		{
			 return ResponseEntity.status(HttpStatus.CONFLICT).body("Personne déjà existante");
		}
		anu.addPerson(newP);
		return ResponseEntity.status(HttpStatus.CREATED).body("http://localhost:8080/entree/"+newP.getId());
	}
/*public String add(@RequestParam String name, @RequestParam String surname,@RequestParam String phone,@RequestParam String city)
	{
		Person p1=new Person(12,name,surname,phone,city);
		anu.addPerson(p1);
		return "redirect:/annuaire/recherche";
	}
	*/
	
	@GetMapping("/annuaire/recherche")
	public String showPerson(@RequestParam(name="name", required=false, defaultValue="*") String name,Model model1) {
		if (name.equals("*")) 
		{
			model1.addAttribute("listperson",anu.getAll());
		}
		else
		{
			model1.addAttribute("listperson",anu.findByName(name));
		}
		return "annuaire/recherche";
	}

	/* @PostMapping("/person")
	  public String personSubmit(@ModelAttribute String name1, Model model1) {
	    model1.addAttribute("listperson",anu.findByName(name1));
	    return "person";
	  }
*/
	
}
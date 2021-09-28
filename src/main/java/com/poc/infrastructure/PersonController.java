package com.poc.infrastructure;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc.donnee.dto.PersonDTO;
import com.poc.service.metier.PersonSM;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/persons")
public class PersonController {

	@Autowired
	private PersonSM personSM;
	
	
	@Operation(summary = "add person")
	@PostMapping("/add")
	public PersonDTO addPerson(@RequestBody PersonDTO personDTO) {
			return personSM.createPerson(personDTO);
	}
	
	@Operation(summary = "update person")
	@PutMapping(value = "/update")
	public PersonDTO updatePerson(HttpServletResponse response, @RequestBody PersonDTO personDTO) {
		return personSM.updatePerson(personDTO);
	}
	
	@Operation(summary = "delete person by Id")
	@DeleteMapping(value = "/delete/{id}")
	public PersonDTO deletePerson(@PathVariable("id") int id) {
		return personSM.deletePerson(id);
	}
	
	@Operation(summary = "Delete all persons")
	@DeleteMapping("/deleteall")
	public PersonDTO deleteAllPerson() {
		return personSM.deleteAllPerson();
	}
	
	@Operation(summary = "find person by Id")
	@GetMapping(value = "/get/{id}")
	public PersonDTO getPersonById(@PathVariable("id") int id) {
		return personSM.getPersonById(id);
	}
	
	@Operation(summary = "get all persons")
	@GetMapping(path = "/listperson")
	public List<PersonDTO> findAllPerson() {
		return personSM.findAllPerson();
	}
	
	@Operation(summary = "find person by search and pagined")
	@GetMapping(path = "/findAllPersonsBySearch")
	public Page<PersonDTO> findAllPersonsBySearch(
			@RequestParam(defaultValue = "") String searchText, 
			@PageableDefault(page = 0, size = 5) Pageable page) {
		return personSM.findAllPersonsBySearch(searchText, page);
	}
	
	@Operation(summary = "get all persons pagined")
	@GetMapping(path = "/listpagined")
	public Page<PersonDTO> findAllPagePersons(@PageableDefault(page = 0, size = 5) Pageable page) {
		return personSM.findAllPagePersons(page);
	}
	
}

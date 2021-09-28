package com.poc.service.metier;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poc.donnee.dto.PersonDTO;

public interface PersonSM {

	PersonDTO createPerson(PersonDTO personDTO);

	PersonDTO deletePerson(int id);

	PersonDTO deleteAllPerson();

	PersonDTO updatePerson(PersonDTO personDTO);

	List<PersonDTO> findAllPerson();

	PersonDTO getPersonById(int id);

	public Page<PersonDTO> findAllPersonsBySearch(String searchText, Pageable page);

	public Page<PersonDTO> findAllPagePersons(Pageable page);

}

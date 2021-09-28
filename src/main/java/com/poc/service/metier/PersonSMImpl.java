package com.poc.service.metier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poc.contrainte.error.ErrorsEnum;
import com.poc.contrainte.error.FunctionalException;
import com.poc.donnee.domain.Person;
import com.poc.donnee.dto.PersonDTO;
import com.poc.mapper.PersonMapper;
import com.poc.service.repository.PersonRepository;

@Service
public class PersonSMImpl implements PersonSM {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PersonMapper personMapper;

	@PostConstruct
	private void initVehicle() {
		List<Person> persons = personRepository.findAll();
		if (persons.isEmpty()) {
			persons = new ArrayList<>();
			List<String> personName = Arrays.asList("john doe", "julien gaï", "matiace legran", "nathan cary",
					"marry vial");
			for (String item : personName) {
				Person data = new Person();
				data.setName(item);
				data.setAge(25);
				data.setEmailId(item + "@gmail.com");
				persons.add(data);
			}

			personRepository.saveAll(persons);
		}
	}

	@Override
	public PersonDTO createPerson(PersonDTO personDTO) {
		return personMapper.personToDTO(personRepository.save(personMapper.dtoToPerson(personDTO)));
	}

	@Override
	public PersonDTO deletePerson(int id) {
		Optional<Person> person = personRepository.findById(id);
		if (!person.isPresent()) {
			throw new FunctionalException(ErrorsEnum.ERR_ID_PERSON);
		}
		personRepository.deleteById(id);
		return personMapper.personToDTO(person.get());
	}

	@Override
	public PersonDTO deleteAllPerson() {
		personRepository.deleteAll();
		return null;
	}

	@Override
	public PersonDTO updatePerson(PersonDTO personDTO) {
		Optional<Person> person = personRepository.findById(personDTO.getId());
		if (person.isPresent()) {
			return personMapper.personToDTO(personRepository.save(personMapper.dtoToPerson(personDTO)));
		}
		throw new FunctionalException(ErrorsEnum.ERR_PERSON_INVALID);
	}

	@Override
	public List<PersonDTO> findAllPerson() {
		List<Person> list = personRepository.findAll();
		List<PersonDTO> listDto;
		if (!list.isEmpty()) {
			listDto = personMapper.toListPersonDTO(list);
			return listDto;
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public PersonDTO getPersonById(int id) {
		Optional<Person> person = personRepository.findById(id);
		if (!person.isPresent()) {
			throw new FunctionalException(ErrorsEnum.ERR_ID_PERSON);
		}
		return personMapper.personToDTO(person.get());
	}

	@Override
	public Page<PersonDTO> findAllPersonsBySearch(String searchText, Pageable page) {
		if (searchText.equals("")) {
			Page<Person> persons = personRepository.findAll(page);
			return personMapper.toPagePersonDTO(persons, page);
		} else {
			Page<Person> persons = personRepository.findAllPersons(searchText, page);
			return personMapper.toPagePersonDTO(persons, page);
		}
	}

	@Override
	public Page<PersonDTO> findAllPagePersons(Pageable page) {
		Page<Person> persons = personRepository.findAll(page);
		return personMapper.toPagePersonDTO(persons, page);
	}

}

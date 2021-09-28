package com.poc.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.poc.donnee.domain.Person;
import com.poc.donnee.dto.PersonDTO;

@Mapper(componentModel = "spring")
public interface PersonMapper {

	Person dtoToPerson(PersonDTO personDTO);

	PersonDTO personToDTO(Person person);

	List<PersonDTO> toListPersonDTO(List<Person> persons);

	default Page<PersonDTO> toPagePersonDTO(Page<Person> persons, Pageable page) {
		List<PersonDTO> personsDTO = toListPersonDTO(persons.getContent());
		return new PageImpl<>(personsDTO, page, persons.getTotalElements());
	}
}

package com.poc.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poc.donnee.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	Page<Person> findAll(Pageable page);
	
	@Query("FROM Person p WHERE p.name LIKE %:searchText% OR p.emailId LIKE %:searchText% ORDER BY p.id ASC")
	Page<Person> findAllPersons(@Param("searchText") String searchText, Pageable page);
	
}

package com.springintegration.annotation.repository;

import org.springframework.data.repository.CrudRepository;

import com.springintegration.annotation.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}

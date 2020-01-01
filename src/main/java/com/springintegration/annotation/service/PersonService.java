package com.springintegration.annotation.service;

import java.util.List;

import com.springintegration.annotation.dto.PersonDto;

public interface PersonService {
	
	List<PersonDto> read();
	
	PersonDto findById(Integer id);

	void create(PersonDto personDto);

}

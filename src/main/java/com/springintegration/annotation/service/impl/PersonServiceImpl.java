package com.springintegration.annotation.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.springintegration.annotation.dto.PersonDto;
import com.springintegration.annotation.model.Person;
import com.springintegration.annotation.repository.PersonRepository;
import com.springintegration.annotation.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	PersonRepository personRepository;

	@Override
	public List<PersonDto> read() {
		Iterable<Person> personIter = personRepository.findAll();
		List<PersonDto> PersonDtoList = new ArrayList<PersonDto>();
		
		copyPersonIterToList(personIter, PersonDtoList);
		
		return PersonDtoList;
	}
	

	@ServiceActivator(inputChannel = "findByIdRequest", outputChannel = "findByIdResponse")
	@Override
	public PersonDto findById(@Payload Integer id) {
		Optional<Person> person = personRepository.findById(id);
		
		PersonDto personDto = new PersonDto();
		
		if (person.isPresent()) {
			BeanUtils.copyProperties(person.get(), personDto);
		}
		
		return personDto;
		
	}
	
	@ServiceActivator(inputChannel = "createMQRequest")
	@Override
	public void create(@Payload PersonDto personDto) {
		Person person = new Person();
		BeanUtils.copyProperties(personDto, person);
		
		person = personRepository.save(person);
		
	}

	private void copyPersonIterToList(Iterable<Person> personIter, List<PersonDto> personDtoList) {
		personIter.forEach(f -> {
			PersonDto d = new PersonDto();
			BeanUtils.copyProperties(f, d);
			personDtoList.add(d);
			}
		);	
	}

}

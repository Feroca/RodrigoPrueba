package com.rodrigo.rest.webservices.restfulwebservices.controller;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rodrigo.rest.webservices.restfulwebservices.entity.SuperHeroe;
import com.rodrigo.rest.webservices.restfulwebservices.service.SuperHeroeDaoService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperHeroeControllerTest {

	@Autowired
	private SuperHeroeDaoService service;
	
	private List<SuperHeroe> superHeroes;
	
	@BeforeEach
	public void setUp() {
		superHeroes.add(new SuperHeroe(1,"Batman"));
		superHeroes.add(new SuperHeroe(2,"SuperMan"));
		superHeroes.add(new SuperHeroe(3,"IronMan"));
		superHeroes.add(new SuperHeroe(4,"Hulk"));
	}

	@Test
	public void retrieveAllSuperHeroes() {
		List<SuperHeroe> superHeroes = new ArrayList<>();
		superHeroes.add(new SuperHeroe(1,"Batman"));
		superHeroes.add(new SuperHeroe(2,"SuperMan"));
		superHeroes.add(new SuperHeroe(3,"IronMan"));
		superHeroes.add(new SuperHeroe(4,"Hulk"));
		
		Mockito.when(service.findAll()).thenReturn(superHeroes);
		List<SuperHeroe> list = service.findAll();
		
		assertThat(list).isNotEmpty();
	}
	
	@Test
	public void retrieveSuperHeroe() {
		Integer id = 1;
		
		assertThat(service.findOne(id)).isNotNull();
		
	}
	
	
}

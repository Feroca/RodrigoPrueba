package com.rodrigo.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rodrigo.rest.webservices.restfulwebservices.IRespository.SuperHeroesRespository;
import com.rodrigo.rest.webservices.restfulwebservices.entity.SuperHeroe;
import com.rodrigo.rest.webservices.restfulwebservices.exception.SuperHeroeNotFoundException;

import jakarta.validation.Valid;



@RestController
public class SuperHeroesJpa {
	
	@Autowired
	private SuperHeroesRespository heroesRepository;
	
	@GetMapping("/jpa/superHeroes")
	public ResponseEntity<List<SuperHeroe>> retrieveSuperHeroes() {
		List<SuperHeroe> listSuperHeroes = heroesRepository.findAll();
		return new ResponseEntity<List<SuperHeroe>>(listSuperHeroes, HttpStatus.OK);
	}
	
	@GetMapping("/jpa/superHeroes/{id}")
	public ResponseEntity<SuperHeroe> retrieveHeroe(@PathVariable Integer id){
		Optional<SuperHeroe> superHeroe = heroesRepository.findById(id);
		
		if(!superHeroe.isPresent())
			throw new SuperHeroeNotFoundException("SuperHeroe not found with id-" + id);
		
		return new ResponseEntity<SuperHeroe>(superHeroe.get(), HttpStatus.OK);
	}

	
	@DeleteMapping("/jpa/superHeroes/{id}")
	public ResponseEntity<String> deleteSuperHeroe(@PathVariable Integer id) {
		Optional<SuperHeroe> superHeroe = heroesRepository.findById(id);
		
		if(!superHeroe.isPresent())
			throw new SuperHeroeNotFoundException("SuperHeroe not found with id-" + id);
		
		heroesRepository.deleteById(id);
		
		return new ResponseEntity<String>("SuperHeroe with id '" + id + "' has been deleted", HttpStatus.OK);
	}
	
	
	@PostMapping("/jpa/superHeroes")
	public ResponseEntity<Object> createUser(@Valid @RequestBody SuperHeroe superHeroe) {
		SuperHeroe savedSuperHeroe = heroesRepository.save(superHeroe);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedSuperHeroe.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PostMapping("/jpa/superHeroes/{id}")
	public ResponseEntity<String> updateSuperHeroe( @PathVariable Integer id, @Valid @RequestBody SuperHeroe superHeroe){
		Optional<SuperHeroe> superHeroeFromBDOptional = heroesRepository.findById(id);
		
		if(!superHeroeFromBDOptional.isPresent())
			throw new SuperHeroeNotFoundException("id-" + id);
		
		SuperHeroe superHeroeFromBD  = superHeroeFromBDOptional.get();
		superHeroeFromBD.setName(superHeroe.getName());
		heroesRepository.updateHeroeById(superHeroe.getId(), superHeroe.getName());
		
		return new ResponseEntity<String>("SuperHeroe with id '" + id + "' has been updated", HttpStatus.OK);
	}
	
	@GetMapping("/jpa/superHeroes/name/{name}")
	public ResponseEntity<List<SuperHeroe>> retrieveHeroe(@PathVariable String name){
		List<SuperHeroe> listSuperHeroes = heroesRepository.getSuperHeroesByName(name);
		
		return new ResponseEntity<List<SuperHeroe>>(listSuperHeroes, HttpStatus.OK);
		
	}
}

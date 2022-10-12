package com.rodrigo.rest.webservices.restfulwebservices.controller;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rodrigo.rest.webservices.restfulwebservices.entity.SuperHeroe;
import com.rodrigo.rest.webservices.restfulwebservices.exception.SuperHeroeNotFoundException;
import com.rodrigo.rest.webservices.restfulwebservices.service.SuperHeroeDaoService;

import jakarta.validation.Valid;

@RestController
public class SuperHeroeController {

	private SuperHeroeDaoService service;

	public SuperHeroeController(SuperHeroeDaoService service) {
		this.service = service;
	}

	@GetMapping("/superHeroes")
	public List<SuperHeroe> retrieveAllSuperHeroes() {
		return service.findAll();
	}
	
	@GetMapping("/superHeroes/{id}")
	public SuperHeroe retrieveSuperHeroe(@PathVariable int id) {
		SuperHeroe superHeroe = service.findOne(id);
		
		if(superHeroe==null)
			throw new SuperHeroeNotFoundException("SuperHeroe not found with id:"+id);
		
		return superHeroe;
	}
	
	@DeleteMapping("/superHeroes/{id}")
	public ResponseEntity<String> deleteSuperHeroe(@PathVariable int id) {
		SuperHeroe superHeroe = service.findOne(id);
		
		if(superHeroe==null)
			throw new SuperHeroeNotFoundException("SuperHeroe not found with id:"+id);
		service.deleteById(id);
		
		return new ResponseEntity<String>("SuperHeroe with id '" + id + "' has been deleted", HttpStatus.OK);
	}

	@PostMapping("/superHeroes")
	public ResponseEntity<SuperHeroe> createSuperHeroe(@Valid @RequestBody SuperHeroe superHeroe) {
		
		SuperHeroe savedSuperHeroe = service.save(superHeroe);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedSuperHeroe.getId())
						.toUri();   
		
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/superHeroes/{id}")
	public ResponseEntity<String> updateSuperHeroe( @PathVariable Integer id, @Valid @RequestBody SuperHeroe superHeroe){
		boolean isUpdateed = service.updateSuperHeroe(id, superHeroe);
		
		if(!isUpdateed)
			throw new SuperHeroeNotFoundException("SuperHeroe not found with id:"+id);
		
		return new ResponseEntity<String>("Studen with id '" + id + "' has been updated", HttpStatus.OK);
	}
	
	@GetMapping("/superHeroes/name/{name}")
	public ResponseEntity<List<SuperHeroe>> getSuperHeroesByName(@PathVariable String name){
		List<SuperHeroe> listSuperHeroes = service.getSuperHeroesByName(name);
		
		return new ResponseEntity<List<SuperHeroe>>(listSuperHeroes, HttpStatus.OK);
		
	}
}

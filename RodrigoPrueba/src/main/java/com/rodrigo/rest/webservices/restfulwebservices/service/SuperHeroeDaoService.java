package com.rodrigo.rest.webservices.restfulwebservices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rodrigo.rest.webservices.restfulwebservices.entity.SuperHeroe;
import com.rodrigo.rest.webservices.restfulwebservices.exception.SuperHeroeNotFoundException;

import jakarta.validation.Valid;

@Component
public class SuperHeroeDaoService {

	private static List<SuperHeroe> superHeroes = new ArrayList<>();
	
	private static int superHeroeCount = 0;
	
	static {
		superHeroes.add(new SuperHeroe(++superHeroeCount,"Batman"));
		superHeroes.add(new SuperHeroe(++superHeroeCount,"SuperMan"));
		superHeroes.add(new SuperHeroe(++superHeroeCount,"IronMan"));
		superHeroes.add(new SuperHeroe(++superHeroeCount,"Hulk"));
	}
	
	public List<SuperHeroe> findAll() {
		return superHeroes;
	}
	
	public SuperHeroe save(SuperHeroe superHeroe) {
		superHeroe.setId(++superHeroeCount);
		superHeroes.add(superHeroe);
		return superHeroe;
	}

	public SuperHeroe findOne(Integer id) {
		Predicate<? super SuperHeroe> predicate = superHeroe -> superHeroe.getId().equals(id); 
		return superHeroes.stream().filter(predicate).findFirst().orElse(null);
	}

	public void deleteById(Integer id) {
		Predicate<? super SuperHeroe> predicate = superHeroe -> superHeroe.getId().equals(id);
		superHeroes.removeIf(predicate);
	}

	public boolean updateSuperHeroe(Integer id, SuperHeroe superHeroe){
		boolean isUpdated = false;
		
		SuperHeroe superHeroeFind = findOne(id);
		
		if(superHeroeFind != null) {
			superHeroeFind.setName(superHeroe.getName());
			isUpdated = true;
		}
		
		return  isUpdated;
	}
	
	@GetMapping("/superHeroes/name/{name}")
	public List<SuperHeroe> getSuperHeroesByName(@PathVariable String name){
		Predicate<? super SuperHeroe> predicate = superHeroe -> superHeroe.getName().contains(name); 
		return superHeroes.stream().filter(predicate).toList();
		
	}
	
}

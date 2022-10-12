package com.rodrigo.rest.webservices.restfulwebservices.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity(name = "superheroes")
public class SuperHeroe {
	
	public SuperHeroe() {
		super();
	}

	public SuperHeroe(Integer id, @Size(min = 2, message = "Name should have atleast 2 characters") String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Size(min=2, message="Name should have atleast 2 characters")
	private String name;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
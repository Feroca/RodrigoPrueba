package com.rodrigo.rest.webservices.restfulwebservices.IRespository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rodrigo.rest.webservices.restfulwebservices.entity.SuperHeroe;

import jakarta.transaction.Transactional;



@Repository
public interface SuperHeroesRespository  extends JpaRepository<SuperHeroe, Integer>{

	@Transactional
	@Modifying
	@Query(value = "update superheroes s set s.name = :name where id = :id", nativeQuery = true)
	void updateHeroeById(@Param("id") Integer id, @Param("name") String name);
	
	
	@Query(value = "select id, name from superheroes where name like %?1%",  nativeQuery = true)
	List<SuperHeroe> getSuperHeroesByName(String name);
	
}

package com.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.model.pet.Pet;
import com.java.model.pet.PetType;


@Repository
public interface PetRepo extends JpaRepository<Pet, Long> {

	@Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
    @Transactional(readOnly = true)
    List<PetType> findPetTypes();
	
	
	@Transactional(readOnly = true)
    Pet findById(Integer id);
	
}

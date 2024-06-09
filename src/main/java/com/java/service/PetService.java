package com.java.service;

import java.util.List;

import com.java.model.pet.Pet;
import com.java.model.pet.PetType;

public interface PetService {

    List<PetType> findPetTypes();

    Pet findById(Integer id);

    List<Pet> findAllPet();
    
    Pet  addPet(Pet pet);
    
    Pet  updatePet(Long id ,Pet pet);
    
    void deletePet(Long id);
	
}

package com.java.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.java.exception.NotFoundException;
import com.java.model.pet.Pet;
import com.java.model.pet.PetType;
import com.java.repository.PetRepo;
import com.java.service.PetService;

@Service
public class PetSeviceImpl implements PetService {

	
	@Autowired
	private PetRepo petRepo ;
	
	@Override
	public List<PetType> findPetTypes() {
		return petRepo.findPetTypes();
	}

	@Override
	public Pet findById(Integer id) {
		Pet pet = petRepo.findById(id);
		if(pet == null) {
			throw new NotFoundException("pet with id : "+ id + " not found");
		}
		return pet ;
	}

	@Cacheable(cacheNames = "pet")
	@Override
	public List<Pet> findAllPet() {
		return petRepo.findAll();
	}

	@CacheEvict(allEntries = true ,cacheNames = "pet")

	@Override
	public Pet addPet(Pet pet) {
		return petRepo.save(pet);
	}

	@CacheEvict(allEntries = true , cacheNames = "pet")
	@Override
	public Pet updatePet(Long id, Pet pet) {
		Pet petData = petRepo.findById(id).orElse(null);
		if (petData == null ) {
			throw new NotFoundException("pet with id : "+ id + " not found");
		}
		petData.setName(pet.getName());
		petData.setOwner(pet.getOwner());
		petData.setType(pet.getType());
		petData.setBirthDate(pet.getBirthDate());

		return petRepo.save(petData);
	}

	
	@CacheEvict(allEntries = true , cacheNames = "pet")
	@Override
	public void deletePet(Long id) {
		Optional<Pet> pet = petRepo.findById(id);
		if(!pet.isPresent()) {
			throw new NotFoundException("pet with id : "+ id + " not found");
		}
		petRepo.deleteById(id);
	}

}

package com.java.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.java.model.pet.Pet;
import com.java.service.PetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class PetController {

	
	private PetService petService ;
	
	
	@GetMapping
	public ResponseEntity<List<Pet>>  getAllPet() {
		return ResponseEntity.ok(petService.findAllPet());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pet> getPetById(@PathVariable Integer id){
		return  ResponseEntity.ok(petService.findById(id));
	}
	
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping
	public Pet addPet(Pet pet){
		return  petService.addPet(pet) ;
	}
	
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@PutMapping("/{id}")
	public Pet updatePet(Long id , Pet pet){
		return  petService.updatePet(id , pet) ;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePet(@PathVariable Long id){
		petService.deletePet(id);
		return ResponseEntity.ok("pet with id :" + id  + " deleted successfully !");
	}
	
	
}

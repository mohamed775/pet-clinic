package com.java.controller;

import java.util.Collection;
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

import com.java.model.vet.Vet;
import com.java.service.VetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/vets")
@RequiredArgsConstructor
public class VetController {

	
	private VetService vetService ;
	
	
	@GetMapping
	public ResponseEntity<List<Vet>>  getAllVet() {
		return ResponseEntity.ok(vetService.findAllVet());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Vet> getVetById(@PathVariable Long id){
		return  ResponseEntity.ok(vetService.findVetById(id));
	}
	
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping
	public Vet addOwner(Vet vet){
		return  vetService.addVet(vet) ;
	}
	
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@PutMapping("/{id}")
	public Vet updateOwner(Long id , Vet vet){
		return  vetService.updateVet(id , vet) ;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOwner(@PathVariable Long id){
		vetService.deleteVet(id);
		return ResponseEntity.ok("vet with id :" + id  + " deleted successfully !");
	}
	
	
}

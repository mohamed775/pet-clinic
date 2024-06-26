package com.java.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.java.model.owner.Owner;
import com.java.service.OwnerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {

	
	private final OwnerService ownerService ;
	
	
	@GetMapping
	public ResponseEntity<List<Owner>>  getAllOwner() {
		return ResponseEntity.ok(ownerService.findAllOwner());
	}
	
	@GetMapping("/{id}")
	public Owner getOwnerById(@PathVariable Long id){
		return ownerService.findOwnerById(id);
	}
	
	
	@GetMapping("/lastname/{lastName}")
	public ResponseEntity<Collection<Owner>> getOwnerByLastName(@PathVariable  String lastName){
		return  ResponseEntity.ok(ownerService.findByLastName(lastName));
	}
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping
	public Owner addOwner( @RequestBody @Valid Owner owner){
		return  ownerService.addOwner(owner) ;
	}
	
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@PutMapping("/{id}")
	public Owner updateOwner(@PathVariable Long id ,@RequestBody @Valid Owner owner){
		return  ownerService.updateOwner(id , owner) ;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOwner(@PathVariable Long id){
		ownerService.deleteOwner(id);
		return ResponseEntity.ok("Owner with id :" + id  + " deleted successfully !");
	}
	
	
	
}

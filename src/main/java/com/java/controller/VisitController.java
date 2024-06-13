package com.java.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.java.model.visit.Visit;
import com.java.service.VisitService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitController {

	
	
	private final VisitService visitService;
	
	
	@GetMapping
	public ResponseEntity<List<Visit>>  getAllVisit() {
		return ResponseEntity.ok(visitService.findAllVisit());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Visit> getVisitById(@PathVariable Long id){
		return  ResponseEntity.ok(visitService.findVisitById(id));
	}
	
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping
	public Visit addVisit(@RequestBody Visit visit){
		return  visitService.addVisit(visit) ;
	}
	
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@PutMapping("/{id}")
	public Visit updateOwner(@PathVariable Long id ,@RequestBody Visit visit){
		return  visitService.updateVisit(id , visit) ;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOwner(@PathVariable Long id){
		visitService.deleteVisit(id);
		return ResponseEntity.ok("visit with id :" + id  + " deleted successfully !");
	}
	
	
}

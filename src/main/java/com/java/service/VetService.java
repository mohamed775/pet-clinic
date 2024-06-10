package com.java.service;


import java.util.List;

import com.java.model.vet.Vet;

public interface VetService {

    List<Vet> findAllVet();
	
    Vet findVetById(Long id);
	
	Vet addVet(Vet vet);
	
	Vet updateVet(long id , Vet vet);
	
	void deleteVet(Long id); 
	
}

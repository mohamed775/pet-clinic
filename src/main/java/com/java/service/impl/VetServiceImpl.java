package com.java.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.java.exception.NotFoundException;
import com.java.model.vet.Vet;
import com.java.repository.VetRepo;
import com.java.service.VetService;

@Service
public class VetServiceImpl implements VetService {

	
	@Autowired
	private VetRepo  vetRepo ;
	
	
	@Cacheable(cacheNames = "vet")
	@Override
	public List<Vet> findAllVet() {
		return vetRepo.findAll();
	}

	@Cacheable(cacheNames = "vet")
	@Override
	public Vet findVetById(Long id) {
		
		Optional<Vet> vet = vetRepo.findById(id);
		if(!vet.isPresent()) {
			throw new NotFoundException("Vet with id : "+ id + " not found");
		}
		return vet.get() ;
	}

	
	@CacheEvict(allEntries = true , cacheNames ="vet" )
	@Override
	public Vet addVet(Vet vet) {
		return vetRepo.save(vet);
	}
	
	@CacheEvict(allEntries = true , cacheNames ="vet")
	@Override
	public Vet updateVet(long id, Vet vet) {
		Vet vetData = vetRepo.findById(id).orElse(null);
		if(vetData== null) {
			throw new NotFoundException("Vet with id : "+ id + " not found");
		}
		vetData.setFirstName(vet.getFirstName());
		vetData.setLastName(vet.getLastName());
		vetData.setSpecialties(vet.getSpecialties());

		return vetRepo.save(vetData);
	}

	
	@CacheEvict(allEntries = true ,cacheNames ="vet")
	@Override
	public void deleteVet(Long id) {
		Optional<Vet> vet = vetRepo.findById(id);
		if(!vet.isPresent()) {
			throw new NotFoundException("Vet with id : "+ id + " not found");
		}
		vetRepo.deleteById(id);
	}

	
	
}

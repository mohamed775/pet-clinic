package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.java.model.vet.Vet;
import com.java.repository.VetRepo;
import com.java.service.VetService;

@Service
public class VetServiceImpl implements VetService {

	
	@Autowired
	private VetRepo  vetRepo ;
	
	
	@Cacheable(cacheNames = "vets")
	@Override
	public List<Vet> findAllVet() {
		return vetRepo.findAll();
	}

	@Cacheable(cacheNames = "vets")
	@Override
	public Vet findVetById(Long id) {
		return vetRepo.findById(id).get();
	}

	
	@CacheEvict(allEntries = true , cacheNames ="vets" )
	@Override
	public Vet addVet(Vet vet) {
		return vetRepo.save(vet);
	}
	
	@CacheEvict(allEntries = true , cacheNames ="vets")
	@Override
	public Vet updateVet(long id, Vet vet) {
		Vet vetData = vetRepo.findById(id).orElse(null);
		if(vetData== null) {
			return null ;
		}
		vetData.setFirstName(vet.getFirstName());
		vetData.setLastName(vet.getLastName());
		vetData.setSpecialties(vet.getSpecialties());

		return vetRepo.save(vetData);
	}

	
	@CacheEvict(allEntries = true ,cacheNames ="vets")
	@Override
	public void deleteVet(Long id) {
		vetRepo.deleteById(id);
	}

	
	
}

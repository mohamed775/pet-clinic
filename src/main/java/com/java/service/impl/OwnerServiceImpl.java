package com.java.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.java.exception.NotFoundException;
import com.java.model.owner.Owner;
import com.java.repository.OwnerRepo;
import com.java.service.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService {

	
	@Autowired
	private OwnerRepo ownerRepo ;
	
	
	@Cacheable(cacheNames = "owner")
	@Override
	public List<Owner> findAllOwner() {
		return ownerRepo.findAll();
	}

	@Override
	public Owner findOwnerById(Long id) {
		
		Owner owner = ownerRepo.findById(id).orElse(null);
		if(owner == null) {
			throw new NotFoundException("owner with id : "+ id + " not found");
		}
		return owner ;
	}

	@Cacheable(cacheNames = "owner")
	@Override
	public Collection<Owner> findByLastName(String lastName) {
		
		Collection<Owner> owners = ownerRepo.findByLastNameContaining(lastName);
		if(owners == null) {
			throw new NotFoundException("No owner lastName is "+ lastName );
		}
		return owners ;
	}

	@CacheEvict(allEntries = true ,cacheNames = "owner")
	@Override
	public Owner addOwner(Owner owner) {
		return ownerRepo.save(owner) ;
	}

	@CachePut(cacheNames = "owner")
	@Override
	public Owner updateOwner(long id, Owner owner) {
		Owner owne  = ownerRepo.findById(id).orElse(null);
		if(owne==null) {
			throw new NotFoundException("owner with id : "+ id + " not found");
		}
		owne.setFirstName(owner.getFirstName());
		owne.setLastName(owner.getLastName());
		owne.setCity(owner.getCity());
		owne.setAddress(owner.getAddress());
		owne.setTelephone(owner.getTelephone());
		owne.setPets(owner.getPets());
		

		return ownerRepo.save(owne) ;
	}

	@CacheEvict(cacheNames = "owner")
	@Override
	public void deleteOwner(Long id) {
		Owner owner = ownerRepo.findById(id).orElse(null);
		if(owner == null) {
			throw new NotFoundException("owner with id : "+ id + " not found");
		}
		ownerRepo.deleteById(id);
	}

}

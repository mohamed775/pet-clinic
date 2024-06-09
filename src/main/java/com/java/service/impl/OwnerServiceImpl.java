package com.java.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.java.model.owner.Owner;
import com.java.repository.OwnerRepo;
import com.java.service.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerRepo ownerRepo ;
	
	
	@Cacheable
	@Override
	public List<Owner> findAllOwner() {
		return ownerRepo.findAll();
	}

	@Cacheable
	@Override
	public Owner findOwnerById(Integer id) {
		return ownerRepo.findById(id);
	}

	@Cacheable
	@Override
	public Collection<Owner> findByLastName(String lastName) {
		return ownerRepo.findByLastName(lastName);
	}

	@CacheEvict(allEntries = true)
	@Override
	public Owner addOwner(Owner owner) {
		return ownerRepo.save(owner) ;
	}

	@CachePut
	@Override
	public Owner updateOwner(long id, Owner owner) {
		Owner owne  = ownerRepo.findById(id).orElse(null);
		if(owne==null) {
			return null ;
		}
		owne.setFirstName(owner.getFirstName());
		owne.setLastName(owner.getLastName());
		owne.setCity(owner.getCity());
		owne.setAddress(owner.getAddress());
		owne.setTelephone(owner.getTelephone());
		owne.setPets(owner.getPets());
		

		return ownerRepo.save(owne) ;
	}

	@CacheEvict
	@Override
	public void deleteOwner(Long id) {
		ownerRepo.deleteById(id);
	}

}

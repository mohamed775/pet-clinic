package com.java.service;

import java.util.List;

import com.java.model.owner.Owner;

public interface OwnerService {

	Owner findAllOwner();
	
	Owner findOwnerById(Integer id);
	
	List<Owner> findByLastName(String lastName);
	
	Owner addOwner(Owner owner);
	
	Owner updateOwner(long id , Owner owner);
	
	void deleteOwner(Long id);
	
}

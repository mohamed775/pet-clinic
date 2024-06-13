package com.java.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.model.owner.Owner;

@Repository
public interface OwnerRepo extends JpaRepository<Owner, Long> {

	
//	@Query("SELECT DISTINCT owner from Owner owner left join fetch owner.pets WHERE owner.lastName LIKE :lastName%")
    @Transactional(readOnly = true)
	Collection<Owner> findByLastNameContaining( String lastName);
	
	
//    @Query("SELECT owner FROM Owner owner left join fetch owner.pets WHERE owner.id =:id")
//    @Transactional(readOnly = true)
//    Owner findById(@Param("id") Integer id);
	
    
    
}

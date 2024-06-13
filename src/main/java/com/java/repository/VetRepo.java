package com.java.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.model.vet.Vet;

@Repository
public interface VetRepo extends JpaRepository<Vet, Long> {

	@Transactional(readOnly = true)
    @Cacheable("vet")
    List<Vet> findAll() throws DataAccessException;
	
}

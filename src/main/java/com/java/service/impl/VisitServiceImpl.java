package com.java.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.java.exception.NotFoundException;
import com.java.model.visit.Visit;
import com.java.repository.VisitRepo;
import com.java.service.VisitService;

@Service
public class VisitServiceImpl implements VisitService {

	
	
	@Autowired
	private VisitRepo visitRepo ;
	
	
	@Cacheable(cacheNames = "visit")
	@Override
	public List<Visit> findAllVisit() {
		return visitRepo.findAll();
	}

	@Override
	public Visit findVisitById(Long id) {
		Optional<Visit> visit =  visitRepo.findById(id);
		if(!visit.isPresent()) {
			throw new NotFoundException("visit with id : "+ id + " not found");
		}
		return visit.get();
	}

	@CacheEvict(allEntries = true ,cacheNames = "visit")
	@Override
	public Visit addVisit(Visit visit) {
		return visitRepo.save(visit);
	}

	@CachePut(cacheNames = "visit")
	@Override
	public Visit updateVisit(long id, Visit visit) {
		Visit visitData = visitRepo.findById(id).orElse(null);
		if(visitData == null) {
			throw new NotFoundException("visit with id : "+ id + " not found");
		}
		visitData.setPetId(visit.getPetId());
		visitData.setDate(visit.getDate());
		visitData.setDescription(visit.getDescription());
		
		return visitRepo.save(visitData);
	}

	@CacheEvict(allEntries = true , cacheNames = "visit")
	@Override
	public void deleteVisit(Long id) {
		Optional<Visit> visit =  visitRepo.findById(id);
		if(!visit.isPresent()) {
			throw new NotFoundException("visit with id : "+ id + " not found");
		}
		visitRepo.deleteById(id);
	}

}

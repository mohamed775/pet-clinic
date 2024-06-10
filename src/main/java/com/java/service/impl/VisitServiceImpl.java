package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
		// TODO Auto-generated method stub
		return visitRepo.findById(id).get();
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
			return null;
		}
		visitData.setPetId(visit.getPetId());
		visitData.setDate(visit.getDate());
		visitData.setDescription(visit.getDescription());
		
		return visitRepo.save(visitData);
	}

	@CacheEvict(allEntries = true , cacheNames = "visit")
	@Override
	public void deleteVisit(Long id) {
		visitRepo.deleteById(id);
	}

}

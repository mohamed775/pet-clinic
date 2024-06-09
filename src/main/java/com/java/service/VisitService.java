package com.java.service;

import java.util.List;

import com.java.model.visit.Visit;

public interface VisitService {

	List<Visit> findAllVisit();
	
	Visit findVisitById(Long id);
		
	Visit addVisit(Visit visit);
	
	Visit updateVisit(long id , Visit visit);
	
	void deleteVisit(Long id);
	
}

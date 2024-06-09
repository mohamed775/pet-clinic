package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.model.visit.Visit;

@Repository
public interface VisitRepo extends JpaRepository<Visit, Long> {

	
}

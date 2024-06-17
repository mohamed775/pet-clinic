package com.java.model.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Person extends BaseEntity {

	
	@Column(name = "firstName")
	@NotEmpty(message = "firstName must not be empty")
	private String firstName ;

	
	@Column(name = "lastName")
	@NotEmpty(message = "lastName must not be empty")
	private String lastName ;

	
}

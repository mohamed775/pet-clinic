package com.java.model.owner;



import java.util.Set;

import com.java.model.base.Person;
import com.java.model.pet.Pet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person {

	@Column(name = "address")
	@NotEmpty(message = "address must not be empty")
    private String address;

    @Column(name = "city")
	@NotEmpty(message = "city must not be empty")
    private String city;
    
    @Column(name = "telephone")
	@NotEmpty(message = "telephone must not be empty")
    @Digits(fraction = 0, integer = 10)
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<Pet> pets;
    
	
}

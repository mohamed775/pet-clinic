package com.java.model.pet;

import com.java.model.base.NamedEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "types")
public class PetType extends NamedEntity {

}

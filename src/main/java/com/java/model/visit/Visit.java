package com.java.model.visit;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.java.model.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

	@Column(name = "visit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date = LocalDate.now();

	@NotEmpty(message = "description must not be empty")
    @Column(name = "description")
    private String description;

    @Column(name = "pet_id")
    private Integer petId;
}

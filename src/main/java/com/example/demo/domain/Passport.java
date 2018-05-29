package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Passport {

	@Id
	@GeneratedValue
	private int id;

	String number;

	@OneToOne(mappedBy = "passport")
	Person person;

	public String toString() {
		return "Passport: number:" + number + ", person: " + person.getName();
	}
}

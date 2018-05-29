package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Address {

	@Id
	@GeneratedValue
	private int address_id;

	private String street;

	@ManyToOne
	@JoinColumn(name = "person_id")
	Person person;

	public String toString() {
		return "Address: address_id" + address_id + ", Street:" + street + ", person: " + person.getName();
	}
}

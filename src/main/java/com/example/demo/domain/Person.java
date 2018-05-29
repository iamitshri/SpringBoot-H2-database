package com.example.demo.domain;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Person {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "person_name")
	private String name;

	@OneToMany(mappedBy = "person")
	List<Address> addresses;

	@OneToOne
	@JoinColumn(name = "passport_id")
	Passport passport;

	@ManyToMany(mappedBy = "persons")
	List<Course> courses;

	public String toString() {

		return "id:" + id + ", name:" + name + ", address:"
				+ String.join("--", addresses.stream().map(addr -> addr.getStreet()).collect(Collectors.toList()));
	}
}

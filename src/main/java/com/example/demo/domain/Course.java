package com.example.demo.domain;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class Course {

	@Id
	@GeneratedValue
	private int id;

	private String courseName;

	@ManyToMany
	@JoinTable(name = "ManyToManyBtwPersonAndCourse", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
	List<Person> persons;

	public String toString() {
		return " Course: courseName:" + courseName + ", person: "
				+ String.join("--", persons.stream().map(person -> person.getName()).collect(Collectors.toList()));
	}

}

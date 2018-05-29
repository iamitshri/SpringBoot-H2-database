package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Address;
import com.example.demo.domain.Course;
import com.example.demo.domain.Passport;
import com.example.demo.domain.Person;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class DemoApplication implements CommandLineRunner {

	@Autowired
	EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void run(String... args) throws Exception {
		System.out.println("START CommandLineRunner");

		List<Course> courses = new ArrayList<>();
		Course cs1 = new Course();
		cs1.setCourseName("intro to computing");
		courses.add(cs1);

		List<Person> persons = new ArrayList<>();
		cs1.setPersons(persons);
		Person person = new Person();
		person.setName("Amit");
		person.setCourses(courses);
		persons.add(person);

		List<Address> addresses = new ArrayList<>();
		Address addr = new Address();
		addr.setStreet("sat rasta");
		addresses.add(addr);
		person.setAddresses(addresses);
		addr.setPerson(person);

		Passport passport = new Passport();
		passport.setNumber("12");
		passport.setPerson(person);
		person.setPassport(passport);

		em.persist(cs1);
		em.persist(passport);
		em.persist(person);
		em.persist(addr);
		log.debug("Person Name {} and Id {}",person.getName(),person.getId());
		em.flush();
	
		Person p2 = em.find(Person.class, person.getId());
		em.find(Person.class, person.getId(), LockModeType.PESSIMISTIC_READ);
		log.debug("cached obj:{}", p2.toString());
		log.debug(person.toString());
		em.refresh(person);
		p2 = em.find(Person.class, person.getId());
		
		System.out.println("STOP CommandLineRunner");
		
		
	}

}

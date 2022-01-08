package fr.imt.TPSpringBoot;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
	List<Person> findAll();
	Person findById(int id);
	List<Person> findByName(String lastName);
}

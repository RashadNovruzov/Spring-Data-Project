package ru.rashad.springproject.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.rashad.springproject.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person,Integer> {
	Optional<Person> findByFullName(String name);
}

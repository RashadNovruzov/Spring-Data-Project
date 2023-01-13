package ru.rashad.springproject.services;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.rashad.springproject.models.Book;
import ru.rashad.springproject.models.Person;
import ru.rashad.springproject.repositories.PeopleRepository;

@Service
@Transactional(readOnly=true)
public class PeopleService {

	private PeopleRepository peopleRepository;
	private BooksService bookService;
	
	@Autowired
	public PeopleService(PeopleRepository peopleRepository,BooksService bookService) {
		this.peopleRepository = peopleRepository;
		this.bookService = bookService;
	}
	
	public List<Person> findAll(){
		return peopleRepository.findAll();
	}
	
	public Person findById(int id) {
		Date date = new Date();
		long now = date.getTime();
		Person person = peopleRepository.findById(id).get();
		
		return person;
	}
	
	public List<Book> getBooksById(int id){
		Optional<Person> person = peopleRepository.findById(id);
		if(person.isPresent()) {
			Hibernate.initialize(person.get().getBooks());
			for(Book book:person.get().getBooks()) {
				if((new Date().getTime()/1000-book.getDeadline().getTime()/1000)>=60) {
					book.setTime(true);
				}
			}
			return person.get().getBooks();
		}else {
			return Collections.emptyList();
		}
	}
	@Transactional
	public void save(Person person) {
		peopleRepository.save(person);
	}
	
	@Transactional
	public void save(Person updatedPerson, int id) {
		updatedPerson.setId(id);
		peopleRepository.save(updatedPerson);
	}
	
	@Transactional
	public void delete(int id) {
		
		for(Book book:peopleRepository.findById(id).get().getBooks()) {
			book.setOwner(null);
			book.setDeadline(null);
		}
		
		peopleRepository.deleteById(id);
	}
	
	public Optional<Person> findByFullName(String name){
		return peopleRepository.findByFullName(name);
	}
}

package ru.rashad.springproject.services;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.rashad.springproject.models.Book;
import ru.rashad.springproject.repositories.BookRepository;

@Service
@Transactional(readOnly=true)
public class BooksService {

	private BookRepository bookRepo;
	
	@Autowired
	public BooksService(BookRepository bookRepo) {
		this.bookRepo = bookRepo;
	}
	
	public List<Book> findAll(){
		
		return bookRepo.findAll();
	}
	
	public List<Book> findAll(int page,boolean stmnt){
		if(stmnt) {
			return bookRepo.findAll(PageRequest.of(page, 2,Sort.by("year"))).getContent();
		}
		return bookRepo.findAll(PageRequest.of(page, 2)).getContent();
	}
	
	
	public Book findById(int id) {
		return bookRepo.findById(id).orElse(null);
	}
	
	public Set<Book> findByTitleContaining(String bookName){
		Set<Book> books = new HashSet<>();
		
		for(Book book:bookRepo.findByTitleContaining(bookName.toUpperCase())) {
			books.add(book);
		}
		for(Book book:bookRepo.findByTitleContaining(bookName.toLowerCase())) {
			books.add(book);
		}
		return books;
	}
	
	@Transactional
	public void save(Book book) {
		bookRepo.save(book);
	}
	
	@Transactional
	public void save(int id, Book newBook) {
		newBook.setId(id);
		bookRepo.save(newBook);
	}
	
	@Transactional
	public void delete(int id) {
		bookRepo.deleteById(id);
	}
}

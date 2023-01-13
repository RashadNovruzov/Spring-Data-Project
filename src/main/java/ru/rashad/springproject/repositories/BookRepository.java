package ru.rashad.springproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.rashad.springproject.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{

	List<Book> findByTitleContaining(String bookName);
	
}

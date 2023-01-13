package ru.rashad.springproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.rashad.springproject.models.Book;
import ru.rashad.springproject.models.Person;
import ru.rashad.springproject.services.BooksService;
import ru.rashad.springproject.services.PeopleService;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
/*
 * 
 * <p th:style ="${isAdmin} ? 'color: blue' : '' ">
   		Some Text 3
	</p>
 * 
 */
@Controller
@RequestMapping("/books")
public class BooksController {

	private final BooksService bookService;
	private final PeopleService personDAO;

	@Autowired
	public BooksController(BooksService bookService, PeopleService personDAO) {
		this.bookService = bookService;
		this.personDAO = personDAO;
	}

	@GetMapping("")
	public String index(Model model,@RequestParam(name="page",required=false) String page,@RequestParam(name="sortByYear",required=false) String stmnt) {
		List<Book> books=null;
		if(page!=null && stmnt != null) {
			model.addAttribute("num",page);
			model.addAttribute("stmnt",stmnt);		
			books = bookService.findAll(Integer.parseInt(page),Boolean.parseBoolean(stmnt));
		}else {
			model.addAttribute("num",0);
			model.addAttribute("stmnt",false);		
			books = bookService.findAll(0,false);
		}
		model.addAttribute("books",books);
		model.addAttribute("length",bookService.findAll().size()/2);
		return "books/index";
	}

	

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
		Book book = bookService.findById(id);
		model.addAttribute("book", book);

		Person owner = book.getOwner();

		if (owner != null)
			model.addAttribute("owner", owner);
		else
			model.addAttribute("people", personDAO.findAll());

		return "books/show";
	}

	@GetMapping("/new")
	public String newBook(@ModelAttribute("book") Book Book) {
		return "books/new";
	}

	@PostMapping()
	public String create(@ModelAttribute("book") @Valid Book Book, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "books/new";

		bookService.save(Book);
		return "redirect:/books";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("book", bookService.findById(id));
		return "books/edit";
	}

	@PostMapping("/{id}")
	public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
			@PathVariable("id") int id) {
		if (bindingResult.hasErrors())
			return "books/edit";

		bookService.save(id, book);
		return "redirect:/books";
	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable("id") int id) {
		bookService.delete(id);
		return "redirect:/books";
	}

	@PostMapping("/{id}/release")
	public String release(@PathVariable("id") int id) {
		Book book = bookService.findById(id);
		book.setOwner(null);
		book.setDeadline(null);
		bookService.save(id, book);
		return "redirect:/books/" + id;
	}

	@PostMapping("/{id}/assign")
	public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
		Book book = bookService.findById(id);
		book.setOwner(selectedPerson);
		book.setDeadline(new Date());
		bookService.save(id, book);
		return "redirect:/books/" + id;
	}

	@GetMapping("/search")
	public String getSearchPage(@ModelAttribute("bookName") Book bookName, Model model) {
		model.addAttribute("first", true);
		return "books/search";
	}

	@PostMapping("/search")
	public String postSearchPage(@ModelAttribute("bookName") Book bookName, Model model) {
		model.addAttribute("first", false);
		model.addAttribute("books", bookService.findByTitleContaining(bookName.getTitle()));
		return "books/search";
	}

}

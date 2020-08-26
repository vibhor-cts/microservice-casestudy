package com.alphaandomega.books.controller;


import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alphaandomega.books.model.Book;
import com.alphaandomega.books.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("books")
	public List<Book> getBooks() {
		return bookService.getBooks();
	}
	
	@GetMapping("books/{bookId}")
	public ResponseEntity<Book> getBook(@PathVariable String bookId) throws Exception {
		Book book = bookService.getBook(bookId);
		if (Objects.nonNull(book)) {
			return new ResponseEntity<Book>(book, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("books/updateAvailability/{bookId}/{incrementalCount}")
	public ResponseEntity<Book> updateBookAvailability(@PathVariable String bookId, @PathVariable int incrementalCount) {
		try {
			Book book = bookService.updateBookAvailability(bookId, incrementalCount);
			return new ResponseEntity<Book>(book, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
}

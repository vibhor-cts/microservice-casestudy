package com.alphaandomega.books.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphaandomega.books.document.BookDocument;
import com.alphaandomega.books.document.UserDocument;
import com.alphaandomega.books.model.Book;
import com.alphaandomega.books.repository.BookRepository;
import com.alphaandomega.books.repository.UserRepository;

@Service
public class BookService {

	@Autowired 
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Consumer<String, String> consumer;
	
	public List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>();
		List<BookDocument> bookDocuments = bookRepository.findAll();
		
		for (BookDocument bookDocument : bookDocuments) {
			Book book = new Book(bookDocument.getId(), bookDocument.getBookName(), bookDocument.getAuthor(), bookDocument.getCopiesAvailable(), bookDocument.getTotalCopies());
			books.add(book);
		}
		return books;
	}
	
	public Book getBook(String id) {
		Optional<BookDocument> bookDocumentOptional = bookRepository.findById(id);
		
		if (bookDocumentOptional.isPresent()) {
			BookDocument bookDocument = bookDocumentOptional.get();
			Book book = new Book(bookDocument.getId(), bookDocument.getBookName(), bookDocument.getAuthor(), bookDocument.getCopiesAvailable(), bookDocument.getTotalCopies());
			return book;
		}
		return null;
	}

	public Book updateBookAvailability(String bookId, int incrementalCount) throws Exception {
		System.out.println("In BookService with params " + bookId + " " +incrementalCount);
		Optional<BookDocument> bookDocumentOptional = bookRepository.findById(bookId);
		if (bookDocumentOptional.isPresent()) {
			System.out.println("Book with ID "+ bookId + "found in DB");
			BookDocument bookDocument = bookDocumentOptional.get();
			if (bookDocument.getCopiesAvailable() == 0) {
				System.out.println("Current availability for BookId " + bookId + "is 0, will notify to interested subscribers");
				System.out.println("Before poll");
				ConsumerRecords<String, String> records = consumer.poll(1000);
				System.out.println("After poll");
				for(ConsumerRecord<String, String> record : records) {
					System.out.println("Record.Value "+record.value());
					String[] values = record.value().split(" ");
					Optional<UserDocument> user = userRepository.findById(values[1]);
					System.out.println("Notifying user at email address "+user.get().getEmail());
				}
			}
			bookDocument.setCopiesAvailable(bookDocument.getCopiesAvailable() + incrementalCount);
			bookRepository.save(bookDocument);
			return new Book(bookDocument.getId(), bookDocument.getBookName(), bookDocument.getAuthor(), bookDocument.getCopiesAvailable(), bookDocument.getTotalCopies());
		} else {
			System.out.println("Book with given bookId does not exist");
			throw new Exception("book doesn't exist");
		}
	}
}

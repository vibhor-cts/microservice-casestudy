package com.alphaandomega.books.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "books")
public class BookDocument {

	@Id
	@Field(name = "_id")
	private String id;
	
	@Field(name = "book_name")
	private String bookName;
	
	@Field(name = "author")
	private String author;
	
	@Field(name = "available_copies")
	private long copiesAvailable;
	
	@Field(name = "total_copies")
	private long totalCopies;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getCopiesAvailable() {
		return copiesAvailable;
	}

	public void setCopiesAvailable(long copiesAvailable) {
		this.copiesAvailable = copiesAvailable;
	}

	public long getTotalCopies() {
		return totalCopies;
	}

	public void setTotalCopies(long totalCopies) {
		this.totalCopies = totalCopies;
	}
	
	
}

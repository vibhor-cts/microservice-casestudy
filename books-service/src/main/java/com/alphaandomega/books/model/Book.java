package com.alphaandomega.books.model;


public class Book {

	private String bookId;
	
	private String name;
	
	private String author;
	
	private long copiesAvailable;
	
	private long totalCopies;

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Book(String bookId, String name, String author, long copiesAvailable, long totalCopies) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.copiesAvailable = copiesAvailable;
		this.totalCopies = totalCopies;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

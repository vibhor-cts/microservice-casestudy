package com.alphaandomega.subscriptions.document;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subscription")
public class SubscriptionDocument {
	
	private String id;
	
	private String subscriberId;
	
	private String bookId;
	
	private Date dateSubscribed;
	
	private Date dateReturned;
	
	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public Date getDateSubscribed() {
		return dateSubscribed;
	}

	public void setDateSubscribed(Date dateSubscribed) {
		this.dateSubscribed = dateSubscribed;
	}

	public Date getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SubscriptionDocument(String subscriberId, String bookId, Date dateSubscribed, Date dateReturned) {
		super();
		this.subscriberId = subscriberId;
		this.bookId = bookId;
		this.dateSubscribed = dateSubscribed;
		this.dateReturned = dateReturned;
	}

	public SubscriptionDocument() {
		super();
	}

}

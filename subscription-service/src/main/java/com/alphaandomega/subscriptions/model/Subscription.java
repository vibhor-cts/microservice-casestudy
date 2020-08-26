package com.alphaandomega.subscriptions.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Subscription {

	private String subscriberId;
	
	private String bookId;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dateSubscribed;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dateReturned;
	
	private boolean notify;

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

	public boolean isNotify() {
		return notify;
	}

	public void setNotify(boolean notify) {
		this.notify = notify;
	}

	public Subscription(String subscriberId, String bookId, Date dateSubscribed, Date dateReturned) {
		super();
		this.subscriberId = subscriberId;
		this.bookId = bookId;
		this.dateSubscribed = dateSubscribed;
		this.dateReturned = dateReturned;
	}

	public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

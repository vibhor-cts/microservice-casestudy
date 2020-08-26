package com.alphaandomega.subscriptions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alphaandomega.subscriptions.model.Subscription;
import com.alphaandomega.subscriptions.service.SubscriptionService;

@RestController
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;

	@GetMapping("subscriptions")
	public List<Subscription> getSubscriptions(@RequestParam(required = false) String subscriberId, 
			@RequestHeader(required = true, name = "Authorization") String authorization) {
		return subscriptionService.getSubscriptions(subscriberId);
	}
	
	@PostMapping("subscriptions")
	public ResponseEntity<Object> addSubscription(@RequestBody Subscription subscription,
			@RequestHeader(required = true, name = "Authorization") String authorization) {
		try {
			subscriptionService.subscribe(subscription, authorization);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PostMapping("subscriptions/returns")
	public ResponseEntity<Object> returnBook(@RequestBody Subscription subscription,
			@RequestHeader(required = true, name = "Authorization") String authorization) {
		try {
			subscriptionService.returnBook(subscription, authorization);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
//	@Value("${host}")
//	private String host;
	
//	@GetMapping("hello")
//	public ResponseEntity<Object> returnBook() {
//		try {
//			System.out.println(host);
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
//		}
//	}
}

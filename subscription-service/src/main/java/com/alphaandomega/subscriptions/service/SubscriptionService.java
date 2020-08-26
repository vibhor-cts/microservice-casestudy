package com.alphaandomega.subscriptions.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alphaandomega.subscriptions.document.SubscriptionDocument;
import com.alphaandomega.subscriptions.model.Book;
import com.alphaandomega.subscriptions.model.Subscription;
import com.alphaandomega.subscriptions.repository.SubscriptionRepository;

@Service
public class SubscriptionService {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	public List<Subscription> getSubscriptions(String subscriberId){
		List<SubscriptionDocument> subscriptionDocuments = new ArrayList<SubscriptionDocument>();
		List<Subscription> subscriptions = new ArrayList<Subscription>();
		if (Objects.isNull(subscriberId)) {
			subscriptionDocuments = subscriptionRepository.findAll();
		} else {
			subscriptionDocuments = subscriptionRepository.findBySubscriberId(subscriberId);
		}
		
		for(SubscriptionDocument subscriptionDocument : subscriptionDocuments) {
			Subscription subscription = new Subscription(subscriptionDocument.getSubscriberId(), subscriptionDocument.getBookId(), subscriptionDocument.getDateSubscribed(), subscriptionDocument.getDateReturned());
			subscriptions.add(subscription);
		}
		return subscriptions;
	}

	public void subscribe(Subscription subscription, String authorization) throws Exception {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.AUTHORIZATION, authorization);
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<Book> book = restTemplate.exchange("http://GATEWAY/books/"+subscription.getBookId(), HttpMethod.GET, entity, Book.class);
			
			if (book.getBody().getCopiesAvailable() > 0) {
				SubscriptionDocument document = new SubscriptionDocument(subscription.getSubscriberId(), subscription.getBookId(), subscription.getDateSubscribed(), null);
				subscriptionRepository.save(document);
				restTemplate.exchange("http://GATEWAY/books/updateAvailability/"+subscription.getBookId()+"/-1", HttpMethod.POST, entity, Book.class);
			} else {
				System.out.println("Book copy is not available");
				if (subscription.isNotify()) {
					System.out.println("Pushing a message on Kafka Topic for subscriberId "+subscription.getSubscriberId());
					kafkaTemplate.send("notificationTopic", subscription.getBookId() + " " + subscription.getSubscriberId());
				}
				throw new Exception();
			}
		} catch (Exception e) {
			// log the exception
			throw e;
		}
		
	}

	public void returnBook(Subscription subscription, String authorization) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.AUTHORIZATION, authorization);
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			List<SubscriptionDocument> subscriptionDocuments = subscriptionRepository
					.findBySubscriberIdAndBookId(subscription.getSubscriberId(), subscription.getBookId());
			SubscriptionDocument subscriptionDocument = subscriptionDocuments.get(0);
			subscriptionDocument.setDateReturned(subscription.getDateReturned());
			subscriptionRepository.save(subscriptionDocument);
			restTemplate.exchange("http://GATEWAY/books/updateAvailability/" + subscription.getBookId() + "/1",
					HttpMethod.POST, entity, Book.class);
		} catch (Exception e) {
			// log the exception
			throw e;
		}

	}
}

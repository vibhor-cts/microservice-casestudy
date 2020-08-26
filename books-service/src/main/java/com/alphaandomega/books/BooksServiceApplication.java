package com.alphaandomega.books;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@SpringBootApplication
@EnableDiscoveryClient
public class BooksServiceApplication {
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;
	
	@Autowired 
	private ConsumerFactory<String, String> factory;

	public static void main(String[] args) {
		SpringApplication.run(BooksServiceApplication.class, args);
	}
	
	@Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
          ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
          bootstrapServer);
//        props.put(
//          ConsumerConfig.GROUP_ID_CONFIG, 
//          0);
        props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 0);
        props.put(
          ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
          StringDeserializer.class);
        props.put(
          ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
          StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }
	
	@Bean 
	public Consumer<String, String> createConsumer() {
		Consumer<String, String> createConsumer = factory.createConsumer("0", null);
		System.out.println("consumer created");
		List<String> topics = new ArrayList<String>();
		topics.add("notificationTopic-0");
		System.out.println("Before subscribe");
		createConsumer.subscribe(topics);
		return createConsumer;
	}

}

package com.alphaandomega.books.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.alphaandomega.books.document.UserDocument;

@Repository
public interface UserRepository extends MongoRepository<UserDocument, String> {

}

package com.example.DBLab11.repository;

import com.example.DBLab11.model.Passport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportRepository extends MongoRepository<Passport, String> {
}

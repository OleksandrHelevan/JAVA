package com.example.DBLab11.repository;

import com.example.DBLab11.model.EC;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ECRepository extends MongoRepository<EC, String> {
}

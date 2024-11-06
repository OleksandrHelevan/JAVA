package com.example.DBLab11.repository;

import com.example.DBLab11.model.SGC;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SGCRepository extends MongoRepository<SGC, String> {
}

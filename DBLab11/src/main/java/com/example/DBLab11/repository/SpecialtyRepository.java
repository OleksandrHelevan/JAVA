package com.example.DBLab11.repository;

import com.example.DBLab11.model.Specialty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialtyRepository extends MongoRepository<Specialty, String> {
}

package com.example.DBLab11.repository;

import com.example.DBLab11.model.Applicant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApplicantRepository extends MongoRepository<Applicant, String> {
}

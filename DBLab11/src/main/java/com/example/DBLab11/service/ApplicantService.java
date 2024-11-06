package com.example.DBLab11.service;

import com.example.DBLab11.dto.ApplicantDTO;
import com.example.DBLab11.mapper.ApplicantMapper;
import com.example.DBLab11.model.Applicant;
import com.example.DBLab11.repository.ApplicantRepository;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private ApplicantMapper applicantMapper;
    @Autowired
    private MongoTemplate mongoTemplate;


    public List<Bson> getApplicantsBySpecialtyId(String specialtyId) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.lookup("Specialty", "specialty_id", "_id", "specialty_details"),
                Aggregation.unwind("specialty_details"),
                Aggregation.match(Criteria.where("specialty_id").is(specialtyId)),
                Aggregation.project("name", "surname", "specialty_id", "specialty_details.name_of_specialty")
        );

        AggregationResults<Bson> results = mongoTemplate.aggregate(aggregation, "Applicant", Bson.class);
        return results.getMappedResults();
    }

    public List<ApplicantDTO> findAll() {
        return applicantRepository.findAll().stream()
                .map(applicant -> applicantMapper.toDto(applicant))
                .collect(Collectors.toList());
    }


    public ApplicantDTO findById(String id) {
        return applicantRepository.findById(id)
                .map(applicantMapper::toDto)
                .orElse(null);
    }

    public ApplicantDTO save(ApplicantDTO applicantDTO) {
        Applicant applicant = applicantMapper.toEntity(applicantDTO);
        Applicant savedApplicant = applicantRepository.save(applicant);
        return applicantMapper.toDto(savedApplicant);
    }

    public ApplicantDTO update(String id, ApplicantDTO applicantDTO) {
        if (applicantRepository.existsById(id)) {
            Applicant updatedApplicant = applicantRepository.save(applicantMapper.toEntity(applicantDTO));
            return applicantMapper.toDto(updatedApplicant);
        }
        return null;
    }

    public boolean remove(String id) {
        if (applicantRepository.existsById(id)) {
            applicantRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

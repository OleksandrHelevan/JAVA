package com.example.DBLab11.mapper;

import com.example.DBLab11.dto.ApplicantDTO;
import com.example.DBLab11.model.Applicant;
import org.springframework.stereotype.Component;

@Component
public class ApplicantMapper {


    public ApplicantDTO toDto(Applicant applicant){
        ApplicantDTO applicantDTO = new ApplicantDTO();

        applicantDTO.setName(applicant.getName());
        applicantDTO.setSurname(applicant.getSurname());
        return applicantDTO;
    };

    public Applicant toEntity(ApplicantDTO applicantDTO){
        Applicant applicant = new Applicant();

        applicant.setId(applicant.getId());
        applicant.setName(applicantDTO.getName());
        applicant.setSurname(applicantDTO.getSurname());;
        return applicant;
    };
}

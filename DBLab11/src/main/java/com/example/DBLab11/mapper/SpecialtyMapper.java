package com.example.DBLab11.mapper;

import com.example.DBLab11.dto.SpecialtyDTO;
import com.example.DBLab11.model.Specialty;
import org.springframework.stereotype.Component;

@Component
public class SpecialtyMapper {

    public SpecialtyDTO toDto(Specialty specialty) {
        SpecialtyDTO specialtyDTO = new SpecialtyDTO();
        specialtyDTO.setId(specialty.getId());
        specialtyDTO.setCode(specialty.getCode());
        specialtyDTO.setDepartment(specialty.getDepartment());
        specialtyDTO.setNameOfCurriculum(specialty.getNameOfCurriculum());
        specialtyDTO.setNameOfSpecialty(specialty.getNameOfSpecialty());
        return specialtyDTO;
    }

    public Specialty toEntity(SpecialtyDTO specialtyDTO) {
        Specialty specialty = new Specialty();
        specialty.setId(specialtyDTO.getId());
        specialty.setCode(specialtyDTO.getCode());
        specialty.setDepartment(specialtyDTO.getDepartment());
        specialty.setNameOfCurriculum(specialtyDTO.getNameOfCurriculum());
        specialty.setNameOfSpecialty(specialtyDTO.getNameOfSpecialty());
        return specialty;
    }
}

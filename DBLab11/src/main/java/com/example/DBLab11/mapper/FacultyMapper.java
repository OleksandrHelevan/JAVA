package com.example.DBLab11.mapper;

import com.example.DBLab11.dto.FacultyDTO;
import com.example.DBLab11.model.Faculty;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class FacultyMapper {

    public FacultyDTO toDto(Faculty faculty) {
        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.setId(faculty.getId());
        facultyDTO.setContact(faculty.getContact());
        facultyDTO.setDeanName(faculty.getDeanName());
        facultyDTO.setNameOfFaculty(faculty.getNameOfFaculty());
        facultyDTO.setAddress(faculty.getAddress());
        facultyDTO.setDeanSurname(faculty.getDeanSurname());
        return facultyDTO;
    }

    public Faculty toEntity(FacultyDTO facultyDTO) {
        Faculty faculty = new Faculty();
        faculty.setId(facultyDTO.getId() != null ? facultyDTO.getId() : new ObjectId());
        faculty.setContact(facultyDTO.getContact());
        faculty.setDeanName(facultyDTO.getDeanName());
        faculty.setNameOfFaculty(facultyDTO.getNameOfFaculty());
        faculty.setAddress(facultyDTO.getAddress());
        faculty.setDeanSurname(facultyDTO.getDeanSurname());
        return faculty;
    }
}

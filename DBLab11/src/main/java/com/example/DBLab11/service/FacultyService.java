package com.example.DBLab11.service;

import com.example.DBLab11.dto.FacultyDTO;
import com.example.DBLab11.model.Faculty;
import com.example.DBLab11.mapper.FacultyMapper;
import com.example.DBLab11.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private FacultyMapper facultyMapper;


    public FacultyDTO save(FacultyDTO facultyDTO) {
        Faculty faculty = facultyMapper.toEntity(facultyDTO);
        Faculty savedFaculty = facultyRepository.save(faculty);
        return facultyMapper.toDto(savedFaculty);
    }
    public List<FacultyDTO> getAll() {
        return facultyRepository.findAll().stream()
                .map(facultyMapper::toDto)
                .collect(Collectors.toList());
    }

    public FacultyDTO getById(String id) {
        return facultyRepository.findById(id)
                .map(facultyMapper::toDto)
                .orElse(null);
    }
}

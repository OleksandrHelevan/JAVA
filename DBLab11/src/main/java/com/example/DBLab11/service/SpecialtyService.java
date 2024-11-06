package com.example.DBLab11.service;

import com.example.DBLab11.dto.SpecialtyDTO;
import com.example.DBLab11.model.Specialty;
import com.example.DBLab11.mapper.SpecialtyMapper;
import com.example.DBLab11.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private SpecialtyMapper specialtyMapper;

    public SpecialtyDTO save(SpecialtyDTO specialtyDTO) {
        Specialty specialty = specialtyMapper.toEntity(specialtyDTO);
        Specialty savedSpecialty = specialtyRepository.save(specialty);
        return specialtyMapper.toDto(savedSpecialty);
    }

    public List<SpecialtyDTO> getAll() {
        return specialtyRepository.findAll().stream()
                .map(specialtyMapper::toDto)
                .collect(Collectors.toList());
    }

    public SpecialtyDTO getById(String id) {
        return specialtyRepository.findById(id)
                .map(specialtyMapper::toDto)
                .orElse(null);
    }
}

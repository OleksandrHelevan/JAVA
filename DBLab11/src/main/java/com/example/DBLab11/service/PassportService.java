package com.example.DBLab11.service;

import com.example.DBLab11.dto.PassportDTO;
import com.example.DBLab11.model.Passport;
import com.example.DBLab11.mapper.PassportMapper;
import com.example.DBLab11.repository.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassportService {

    @Autowired
    private PassportRepository passportRepository;

    @Autowired
    private PassportMapper passportMapper;

    public PassportDTO save(PassportDTO passportDTO) {
        Passport passport = passportMapper.toEntity(passportDTO);
        Passport savedPassport = passportRepository.save(passport);
        return passportMapper.toDto(savedPassport);
    }

    public List<PassportDTO> getAll() {
        return passportRepository.findAll().stream()
                .map(passportMapper::toDto)
                .collect(Collectors.toList());
    }

    public PassportDTO getById(String id) {
        return passportRepository.findById(id)
                .map(passportMapper::toDto)
                .orElse(null);
    }
}

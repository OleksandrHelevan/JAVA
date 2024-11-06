package com.example.DBLab11.service;

import com.example.DBLab11.dto.SgcDTO;
import com.example.DBLab11.model.SGC;
import com.example.DBLab11.mapper.SGCMapper;
import com.example.DBLab11.repository.SGCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SGCService {

    @Autowired
    private SGCRepository sgcRepository;

    @Autowired
    private SGCMapper sgcMapper;

    public SgcDTO save(SgcDTO sgcDTO) {
        SGC sgc = sgcMapper.toEntity(sgcDTO);
        SGC savedSGC = sgcRepository.save(sgc);
        return sgcMapper.toDto(savedSGC);
    }

    public List<SgcDTO> getAll() {
        return sgcRepository.findAll().stream()
                .map(sgcMapper::toDto)
                .collect(Collectors.toList());
    }

    public SgcDTO getById(String id) {
        return sgcRepository.findById(id)
                .map(sgcMapper::toDto)
                .orElse(null);
    }
}

package com.example.DBLab11.service;

import com.example.DBLab11.dto.EcDTO;
import com.example.DBLab11.model.EC;
import com.example.DBLab11.mapper.ECMapper;
import com.example.DBLab11.repository.ECRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ECService {

    @Autowired
    private ECRepository ecRepository;
    @Autowired
    private ECMapper ecMapper;

    public EcDTO save(EcDTO ecDTO) {
        EC ec = ecMapper.toEntity(ecDTO);
        EC savedEC = ecRepository.save(ec);
        return ecMapper.toDto(savedEC);
    }

    public List<EcDTO> getAll() {
        return ecRepository.findAll().stream()
                .map(ecMapper::toDto)
                .collect(Collectors.toList());
    }

    public EcDTO getById(String id) {
        return ecRepository.findById(id)
                .map(ecMapper::toDto)
                .orElse(null);
    }
}

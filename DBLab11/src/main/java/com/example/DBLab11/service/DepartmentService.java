package com.example.DBLab11.service;

import com.example.DBLab11.dto.DepartmentDTO;
import com.example.DBLab11.model.Department;
import com.example.DBLab11.mapper.DepartmentMapper;
import com.example.DBLab11.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    public DepartmentDTO save(DepartmentDTO departmentDTO) {
        Department department = departmentMapper.toEntity(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        return departmentMapper.toDto(savedDepartment);
    }

    public List<DepartmentDTO> getAll() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public DepartmentDTO getById(String id) {
        return departmentRepository.findById(id)
                .map(departmentMapper::toDto)
                .orElse(null);
    }
}

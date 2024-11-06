package com.example.DBLab11.mapper;

import com.example.DBLab11.dto.DepartmentDTO;
import com.example.DBLab11.model.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentDTO toDto(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();

        departmentDTO.setId(department.getId());
        departmentDTO.setFaculty(department.getFaculty());
        departmentDTO.setNameOfDepartment(department.getNameOfDepartment());
        departmentDTO.setHeadOfDepartment(department.getHeadOfDepartment());

        return departmentDTO;
    }

    public Department toEntity(DepartmentDTO departmentDTO) {
        Department department = new Department();

            department.setId(departmentDTO.getId());

        department.setFaculty(departmentDTO.getFaculty() != null ? departmentDTO.getFaculty() : null);  // Assuming `Faculty` is handled in another mapper
        department.setNameOfDepartment(departmentDTO.getNameOfDepartment());
        department.setHeadOfDepartment(departmentDTO.getHeadOfDepartment());

        return department;
    }
}

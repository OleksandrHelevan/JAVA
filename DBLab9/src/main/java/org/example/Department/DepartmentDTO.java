package org.example.Department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Specialty.SpecialtyDTO;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class DepartmentDTO {
    private String id;
    private String nameOfDepartment;
    private String headOfDepartment;
    List<SpecialtyDTO> specialties = new ArrayList<>();

    @Override
    public String toString() {
        return "Department: " + nameOfDepartment + " " + headOfDepartment;
    }

    public void addSpecialty(SpecialtyDTO specialty) {
        specialties.add(specialty);
    }
}

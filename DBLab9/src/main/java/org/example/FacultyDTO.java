package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class FacultyDTO {
    private String id;
    private String nameOfFaculty;
    private String contact;
    private String address;
    private String deanName;
    private String deanSurname;
    private List<DepartmentDTO> departments = new ArrayList<>();

    @Override
    public String toString() {
        return "Faculty: " + nameOfFaculty + " " + address + " " + deanName + " " + deanSurname + " " + contact;
    }

    public void addDepartment(DepartmentDTO department) {
        departments.add(department);
    }
}

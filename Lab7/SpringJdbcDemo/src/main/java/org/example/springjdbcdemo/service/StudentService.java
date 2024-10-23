package org.example.springjdbcdemo.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.springjdbcdemo.model.Student;
import org.example.springjdbcdemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Map<Student, Map<String, Double>> getStudentsByGrade(double grade) {
        return studentRepository.getStudentsWithCoursesAboveGrade(grade);
    }

    public Map<Student, Double> getAverageGradeForStudents() {
        return studentRepository.getAverageGradeForStudents();
    }

    public List<Student> getStudentsWithPagination(int limit, int offset) {
        return studentRepository.getStudentsWithPagination(limit, offset);
    }
}

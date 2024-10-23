package org.example.springjdbcdemo;

import org.example.springjdbcdemo.model.Student;
import org.example.springjdbcdemo.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SpringJdbcDemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringJdbcDemoApplication.class, args);
        Student student = context.getBean(Student.class);
        student.setFirstName("John");
        student.setLastName("Dough");
        student.setAge(21);

        StudentService service = context.getBean(StudentService.class);
        service.addStudent(student);

        List<Student> students = service.getAllStudents();
        System.out.println(students);

        Map<Student, Map<String, Double>> map1 = service.getStudentsByGrade(80);
        System.out.println(map1 + " " + map1.size());

        Map<Student,Double> map2 = service.getAverageGradeForStudents();
        System.out.println(map2);

        List<Student> students2 = service.getStudentsWithPagination(2,2);
        System.out.println(students2);
    }

}

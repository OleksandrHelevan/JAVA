package org.example.springjdbcdemo.repository;

import lombok.RequiredArgsConstructor;
import org.example.springjdbcdemo.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(Student student) {
        String sql = "insert into students (first_name, last_name, age) values(?,?,?)";
        /* shortened name for executeUpdate */
        int rowsAffected = jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getAge());
        System.out.println("rowsAffected: " + rowsAffected);
    }

    public List<Student> findAll() {
        String sql = "select * from students";

        RowMapper<Student> mapper = new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setFirstName(rs.getString("first_name"));
                s.setLastName(rs.getString("last_name"));
                s.setAge(rs.getInt("age"));
                return s;
            }
        };
        return jdbcTemplate.query(sql, mapper);

//        return jdbcTemplate.query(sql, (rs, rowNum) -> {
//            Student s = new Student();
//            s.setId(rs.getInt("id"));
//            s.setFirstName(rs.getString("first_name"));
//            s.setLastName(rs.getString("last_name"));
//            s.setAge(rs.getInt("age"));
//            return s;
//        });
    }

    public Map<Student, Map<String, Double>> getStudentsWithCoursesAboveGrade(double minGrade) {
        String sql = """
        SELECT DISTINCT s.id, s.first_name, s.last_name, s.age, sc.grade, c.course_name
        FROM students s
        JOIN student_courses sc ON s.id = sc.student_id
        JOIN courses c ON c.id = sc.course_id
        WHERE sc.grade > ?
    """;

        Map<Student, Map<String, Double>> resultMap = new HashMap<>();

        jdbcTemplate.query(sql, (rs, rowNum) -> {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));
            student.setAge(rs.getInt("age"));

            String courseName = rs.getString("course_name");
            double grade = rs.getDouble("grade");

            resultMap.computeIfAbsent(student, k -> new HashMap<>()).put(courseName, grade);

            return student;
        }, minGrade);

        return resultMap;
    }


    public Map<Student, Double> getAverageGradeForStudents() {
        String sql = """
        SELECT s.id, s.first_name, s.last_name, AVG(sc.grade) AS avg_grade
        FROM students s
        JOIN student_courses sc ON s.id = sc.student_id
        GROUP BY s.id, s.first_name, s.last_name
    """;

        Map<Student, Double> resultMap = new HashMap<>();

        jdbcTemplate.query(sql, (rs) -> {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));
            double averageGrade = rs.getDouble("avg_grade");

            resultMap.put(student, averageGrade);
        });

        return resultMap;
    }


    public List<Student> getStudentsWithPagination(int limit, int offset) {
        String sql = """
        SELECT id, first_name, last_name, age
        FROM students
        ORDER BY last_name
        LIMIT ? OFFSET ?
    """;

        return jdbcTemplate.query(sql, new Object[] { limit, offset }, (rs, rowNum) -> {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));
            student.setAge(rs.getInt("age"));
            return student;
        });
    }


}

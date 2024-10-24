# CREATE TABLE students
# (
#     id         serial PRIMARY KEY,
#     first_name VARCHAR(50),
#     last_name  VARCHAR(50),
#     age        INT
# );
#
# CREATE TABLE courses
# (
#     id          INT PRIMARY KEY,
#     course_name VARCHAR(100)
# );
#
# CREATE TABLE student_courses
# (
#     student_id INT,
#     course_id  INT,
#     grade      DECIMAL(5, 2),
#     PRIMARY KEY (student_id, course_id),
#     FOREIGN KEY (student_id) REFERENCES students (id),
#     FOREIGN KEY (course_id) REFERENCES courses (id)
# );

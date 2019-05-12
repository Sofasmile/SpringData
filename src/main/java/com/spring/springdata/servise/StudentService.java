package com.spring.springdata.servise;

import com.spring.springdata.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    void saveStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(Long id);

    Student getStudentById(Long id);

    List<Student> getAllStudents();

    List<Student> getStudentsByBirthday(int month, int day);
}

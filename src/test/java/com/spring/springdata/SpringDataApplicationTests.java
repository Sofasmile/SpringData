package com.spring.springdata;

import com.spring.springdata.config.ApplicationConfig;
import com.spring.springdata.entity.Course;
import com.spring.springdata.entity.Student;
import com.spring.springdata.servise.StudentService;
import lombok.NoArgsConstructor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@NoArgsConstructor
public class SpringDataApplicationTests {

    @Autowired
    private StudentService service;

    private Student student;

    @Before
    public void setUp() {
        student = new Student()
                .setName("Sofia")
                .setAge(20)
                .setCourse(Course.FIFTH)
                .setSpeciality("CS")
                .setEmail("sofia@mail.com")
                .setBirthday(LocalDate.of(1998, 10, 23));
        service.saveStudent(student);
    }

    @After
    public void drop() {
        service.deleteStudent(student.getId());
    }

    @Test
    public void createStudentTest() {
        Student student = new Student()
                .setName("Lena")
                .setAge(25)
                .setCourse(Course.SIXTH)
                .setSpeciality("CS")
                .setEmail("lena@mail.com")
                .setBirthday(LocalDate.of(1997, 11, 20));
        service.saveStudent(student);
        assertTrue(service.getAllStudents().contains(student));
    }

    @Test
    public void updateStudentTest() {
        student.setName("Update");
        service.updateStudent(student);
        Student updated = service.getStudentById(student.getId()).get();
        assertEquals(student.getName(), updated.getName());
    }

    @Test
    public void getStudentByIdTest() {
        Long id = student.getId();
        Student target = service.getStudentById(id).get();
        assertEquals(student, target);
    }

    @Test
    public void deleteStudentTest() {
        Student student = new Student()
                .setName("Olia")
                .setAge(19)
                .setCourse(Course.FIRST)
                .setSpeciality("SI")
                .setEmail("olia@mail.com")
                .setBirthday(LocalDate.of(1999, 11, 19));
        service.saveStudent(student);
        service.deleteStudent(student.getId());
        assertFalse(service.getAllStudents().contains(student));
    }
}

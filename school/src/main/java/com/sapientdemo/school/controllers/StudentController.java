package com.sapientdemo.school.controllers;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sapientdemo.school.graphql.InputStudent;
import com.sapientdemo.school.models.entities.School;
import com.sapientdemo.school.models.entities.Student;
import com.sapientdemo.school.services.SchoolService;
import com.sapientdemo.school.services.StudentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final SchoolService schoolService;

    // Find all students
    @QueryMapping(name = "findAllStudents")
    public List<Student> findAll() {
        return studentService.findAllStudents();
    }

    // Find student by id
    @QueryMapping(name = "findStudentById")
    public Student findById(@Argument(name = "studentId") String id) {
        Long studentId = Long.parseLong(id);

        return studentService.getStudentById(studentId);
    }

    // Create student
    @MutationMapping
    public Student createStudent(@Argument InputStudent inputStudent) {
        Student student = new Student();
        student.setFirstName(inputStudent.getFirstName());
        student.setLastName(inputStudent.getLastName());
        student.setEmail(inputStudent.getEmail());

        // Set school for the student
        Long schoolId = Long.parseLong(inputStudent.getSchoolId());
        School school = schoolService.getSchoolById(schoolId);
        if (school == null) {
            return null;
        }
        student.setSchool(school);

        studentService.saveStudent(student);

        return student;
    }

    // Delete student by id
    @MutationMapping
    public String deleteStudent(@Argument(name = "studentId") String id) {
        Long studentId = Long.parseLong(id);

        return studentService.deleteStudent(studentId).getMessage();
    }
}
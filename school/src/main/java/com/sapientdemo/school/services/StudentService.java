package com.sapientdemo.school.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sapientdemo.school.models.entities.Student;
import com.sapientdemo.school.repositories.StudentRepository;
import com.sapientdemo.school.utils.GenericResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;

    public void saveStudent(Student student) {
        studentRepository.save(student);
        log.info("Student added: {}", student);
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        var student = studentRepository.findById(id).orElse(null);
        return student;
    }

    public GenericResponse deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElse(null);

        if (student == null) {
            return new GenericResponse(false, "Student not found");
        }

        studentRepository.delete(student);
        log.info("Student deleted: {}", student);

        return new GenericResponse(true, "Student deleted successfully");
    }
}

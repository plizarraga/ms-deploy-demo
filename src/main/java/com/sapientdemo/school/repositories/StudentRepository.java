package com.sapientdemo.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapientdemo.school.models.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}

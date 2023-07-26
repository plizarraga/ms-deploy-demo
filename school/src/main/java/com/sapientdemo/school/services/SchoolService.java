package com.sapientdemo.school.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sapientdemo.school.models.entities.School;
import com.sapientdemo.school.repositories.SchoolRepository;
import com.sapientdemo.school.utils.GenericResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public void saveSchool(School school) {
        schoolRepository.save(school);
        log.info("School added: {}", school);
    }

    public List<School> findAllSchools() {
        return schoolRepository.findAll();
    }

    public School getSchoolById(Long id) {
        var school = schoolRepository.findById(id).orElse(null);
        return school;
    }

    public GenericResponse deleteSchool(Long schoolId) {
        School school = schoolRepository.findById(schoolId)
                .orElse(null);

        if (school == null) {
            return new GenericResponse(false, "School not found");
        }

        schoolRepository.delete(school);
        log.info("School deleted: {}", school);

        return new GenericResponse(true, "School deleted successfully");
    }
}

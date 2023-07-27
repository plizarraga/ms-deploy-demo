package com.sapientdemo.school.controllers;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sapientdemo.school.graphql.InputSchool;
import com.sapientdemo.school.models.entities.School;
import com.sapientdemo.school.services.SchoolService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    // Find all schools
    @QueryMapping(name = "findAllSchools")
    public List<School> findAll() {
        return schoolService.findAllSchools();
    }

    // Find school by id
    @QueryMapping(name = "findSchoolById")
    public School findById(@Argument(name = "schoolId") String id) {
        Long schoolId = Long.parseLong(id);

        return schoolService.getSchoolById(schoolId);
    }

    // Create school
    @MutationMapping
    public School createSchool(@Argument InputSchool inputSchool) {
        School school = new School();
        school.setName(inputSchool.getName());
        school.setEmail(inputSchool.getEmail());
        school.setAddress(inputSchool.getAddress());
        school.setDescription(inputSchool.getDescription());

        schoolService.saveSchool(school);

        return school;
    }

    // Delete school by id
    @MutationMapping
    public String deleteSchool(@Argument(name = "schoolId") String id) {
        Long schoolId = Long.parseLong(id);

        return schoolService.deleteSchool(schoolId).getMessage();
    }
}
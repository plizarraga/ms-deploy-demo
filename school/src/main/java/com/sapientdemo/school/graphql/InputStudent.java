package com.sapientdemo.school.graphql;

import lombok.Data;

@Data
public class InputStudent {
    private String firstName;
    private String lastName;
    private String email;
    private String schoolId;
}

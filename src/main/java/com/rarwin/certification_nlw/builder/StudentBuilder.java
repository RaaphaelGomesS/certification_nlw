package com.rarwin.certification_nlw.builder;

import com.rarwin.certification_nlw.entities.Student;

import java.time.LocalDateTime;

public class StudentBuilder {

    public static Student from(String email) {

        return Student.builder()
                .email(email)
                .createAt(LocalDateTime.now())
                .build();
    }
}

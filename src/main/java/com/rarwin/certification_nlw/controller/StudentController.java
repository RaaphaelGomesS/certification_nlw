package com.rarwin.certification_nlw.controller;

import com.rarwin.certification_nlw.entities.Student;
import com.rarwin.certification_nlw.exception.StudentException;
import com.rarwin.certification_nlw.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/register/{email}")
    public Student createNewStudent(@PathVariable String email) throws StudentException {

        return studentService.createStudent(email);

    }
}

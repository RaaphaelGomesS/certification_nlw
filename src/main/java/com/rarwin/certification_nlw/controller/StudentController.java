package com.rarwin.certification_nlw.controller;

import com.rarwin.certification_nlw.dto.StudentDTO;
import com.rarwin.certification_nlw.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    @PostMapping("/verifyHasCertification")
    public String verifyHasCertification(@RequestBody StudentDTO studentDTO) {

        boolean hasCertification = studentService.alreadyHasACertificationForTech(studentDTO);

        if (hasCertification) {
            return "Student already has the certification!";
        } else {
            return "Student can make certification!";
        }
    }
}

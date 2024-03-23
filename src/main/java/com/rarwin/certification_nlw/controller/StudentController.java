package com.rarwin.certification_nlw.controller;

import com.rarwin.certification_nlw.dto.AnswersDTO;
import com.rarwin.certification_nlw.dto.StudentDTO;
import com.rarwin.certification_nlw.entities.Certification;
import com.rarwin.certification_nlw.exception.StudentException;
import com.rarwin.certification_nlw.service.AnswerService;
import com.rarwin.certification_nlw.service.CertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private CertificationService certificationService;

    @Autowired
    private AnswerService answerService;

    @PostMapping("/verifyHasCertification")
    public String verifyHasCertification(@RequestBody StudentDTO studentDTO) throws StudentException {

        certificationService.verifyAlreadyHasACertificationForTech(studentDTO);

        return "Student can make certification!";

    }

    @PostMapping("/certification/answers")
    public Certification verifyAnswersIsCorrect(@RequestBody AnswersDTO answersDTO) throws StudentException {

        return answerService.checkAnswersFromStudent(answersDTO);
    }
}

package com.rarwin.certification_nlw.service;

import com.rarwin.certification_nlw.builder.StudentBuilder;
import com.rarwin.certification_nlw.dto.AnswersDTO;
import com.rarwin.certification_nlw.dto.StudentDTO;
import com.rarwin.certification_nlw.entities.Student;
import com.rarwin.certification_nlw.exception.StudentException;
import com.rarwin.certification_nlw.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CertificationService certificationService;

    public Student createStudent(String email) throws StudentException {

        Optional<Student> studentOptional = studentRepository.getStudentByEmail(email);

        if (studentOptional.isPresent()) {
            throw new StudentException("Email already registered.");
        } else {
            Student student = StudentBuilder.from(email);

            return studentRepository.save(student);
        }
    }

    public Student getStudentAndVerifyHasCertification(AnswersDTO answers) throws StudentException {

        Optional<Student> studentOptional = studentRepository.getStudentByEmail(answers.getEmail());

        if (studentOptional.isPresent()) {
            certificationService.verifyAlreadyHasACertificationForTech(new StudentDTO(answers.getEmail(), answers.getTechnology()));

            return studentOptional.get();
        } else {
            throw new StudentException("Student not exist, you need to register.");
        }
    }
}

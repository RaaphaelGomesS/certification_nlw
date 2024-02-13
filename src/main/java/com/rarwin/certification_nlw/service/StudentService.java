package com.rarwin.certification_nlw.service;

import com.rarwin.certification_nlw.dto.StudentDTO;
import com.rarwin.certification_nlw.entities.Certification;
import com.rarwin.certification_nlw.entities.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    public boolean alreadyHasACertificationForTech(StudentDTO studentDTO) {

        Student student = getStudentByEmail(studentDTO);

        return hasTheCertification(student, studentDTO);
    }

    private boolean hasTheCertification(Student student, StudentDTO studentDTO) {

        List<Certification> certificationList = student.getCertificationList();

        if (!certificationList.isEmpty()) {

            return certificationList.stream().map(certification -> certification.getTech().equals(studentDTO.getTech())).isParallel();

        } else {
            return false;
        }
    }

    private Student getStudentByEmail(StudentDTO studentDTO) {

        try {
            return Student.builder().build();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

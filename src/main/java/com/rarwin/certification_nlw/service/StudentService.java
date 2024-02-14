package com.rarwin.certification_nlw.service;

import com.rarwin.certification_nlw.dto.StudentDTO;
import com.rarwin.certification_nlw.entities.Certification;
import com.rarwin.certification_nlw.entities.Student;
import com.rarwin.certification_nlw.entities.TechnologyIndicator;
import com.rarwin.certification_nlw.exception.StudentException;
import com.rarwin.certification_nlw.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public boolean alreadyHasACertificationForTech(StudentDTO studentDTO) {

        Student student = getStudentByEmail(studentDTO);

        return hasTheCertification(student, studentDTO);
    }

    private boolean hasTheCertification(Student student, StudentDTO studentDTO) {

        List<Certification> certificationList = student.getCertificationList();

        TechnologyIndicator tech = TechnologyIndicator.getByTheName(studentDTO.getTech());

        if (tech == null) {
            throw new StudentException("There is no certification for this tech!");
        }

        if (!certificationList.isEmpty()) {

            return certificationList.stream().map(certification -> certification.getTech() == tech).findFirst().orElse(false);
        } else {
            return false;
        }
    }

    private Student getStudentByEmail(StudentDTO studentDTO) {

        try {
            Optional<Student> student = studentRepository.getStudentByEmail(studentDTO.getEmail());

            if (student.isEmpty()) {
                throw new StudentException("There is no student with this email!");
            }

            return student.get();

        } catch (Exception e) {
            throw new StudentException("Has failed to get student by the email.");
        }
    }
}

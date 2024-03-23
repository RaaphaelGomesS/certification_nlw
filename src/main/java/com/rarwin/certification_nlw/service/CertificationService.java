package com.rarwin.certification_nlw.service;

import com.rarwin.certification_nlw.dto.StudentDTO;
import com.rarwin.certification_nlw.entities.Certification;
import com.rarwin.certification_nlw.entities.TechnologyIndicator;
import com.rarwin.certification_nlw.exception.StudentException;
import com.rarwin.certification_nlw.repository.CertificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificationService {

    @Autowired
    private CertificationRepository certificationRepository;

    public void verifyAlreadyHasACertificationForTech(StudentDTO studentDTO) throws StudentException {

        if (hasTheCertification(studentDTO)) {
            throw new StudentException("Student already get the certification!");
        }
    }

    private boolean hasTheCertification(StudentDTO studentDTO) throws StudentException {

        TechnologyIndicator tech = TechnologyIndicator.getByTheName(studentDTO.getTech());

        if (tech == null) {
            throw new StudentException("There is no certification for this tech!");
        }

        List<Certification> certifications = certificationRepository.findByStudentEmailAndTechnology(studentDTO.getEmail(), tech);

        if (!certifications.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}

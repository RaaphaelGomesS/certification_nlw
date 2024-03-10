package com.rarwin.certification_nlw.builder;

import com.rarwin.certification_nlw.dto.AnswersDTO;
import com.rarwin.certification_nlw.entities.Certification;
import com.rarwin.certification_nlw.entities.TechnologyIndicator;

import java.time.LocalDateTime;
import java.util.UUID;

public class CertificationBuilder {

    public static Certification from(UUID studentId, AnswersDTO answersDTO) {

        return Certification.builder()
                .studentId(studentId != null ? studentId : UUID.randomUUID())
                .tech(TechnologyIndicator.getByTheName(answersDTO.getTechnology()))
                .createAt(LocalDateTime.now())
                .build();
    }
}

package com.rarwin.certification_nlw.builder;

import com.rarwin.certification_nlw.dto.AnswersAndQuestionsDTO;
import com.rarwin.certification_nlw.entities.Answer;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AnswerBuilder {

    public static Answer from(UUID studentId, UUID certificationId, AnswersAndQuestionsDTO answersAndQuestionsDTO) {

        return Answer.builder()
                .questionId(answersAndQuestionsDTO.getQuestionID())
                .studentId(studentId)
                .certificationId(certificationId)
                .alternativeId(answersAndQuestionsDTO.getAlternativeID())
                .isCorrect(answersAndQuestionsDTO.isCorrect())
                .build();
    }

    public static List<Answer> from(UUID studentId, UUID certificationId, List<AnswersAndQuestionsDTO> answersAndQuestionsDTOS) {

        List<Answer> list = answersAndQuestionsDTOS.stream().map(answersAndQuestionsDTO -> from(studentId, certificationId, answersAndQuestionsDTO)).collect(Collectors.toList());

        return list;
    }
}

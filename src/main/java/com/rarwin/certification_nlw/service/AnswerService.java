package com.rarwin.certification_nlw.service;

import com.rarwin.certification_nlw.builder.AnswerBuilder;
import com.rarwin.certification_nlw.builder.CertificationBuilder;
import com.rarwin.certification_nlw.dto.AnswersAndQuestionsDTO;
import com.rarwin.certification_nlw.dto.AnswersDTO;
import com.rarwin.certification_nlw.entities.*;
import com.rarwin.certification_nlw.repository.CertificationRepository;
import com.rarwin.certification_nlw.repository.QuestionRepository;
import com.rarwin.certification_nlw.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnswerService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CertificationRepository certificationRepository;

    public Certification checkAnswersFromStudent(AnswersDTO answers) {

        List<AnswersAndQuestionsDTO> answersAndQuestions = answers.getAnswersAndQuestions();

        List<Question> questions = questionRepository.findByTech(answers.getTechnology());

        answersAndQuestions.stream().forEach(answersAndQuestionsDTO -> {
            Question question = questions.stream().filter(questionEntity -> answersAndQuestionsDTO.getQuestionID().equals(questionEntity.getId())).findFirst().get();

            setAlternativeCorrect(answersAndQuestionsDTO, question.getAlternatives());
        });

        return certificationRepository.save(buildCertification(answers));
    }

    private AnswersAndQuestionsDTO setAlternativeCorrect(AnswersAndQuestionsDTO answersAndQuestionsDTO, List<Alternative> alternatives) {

        Alternative alternative = alternatives.stream().filter(alternativeEntity -> alternativeEntity.isCorrect()).findFirst().get();

        if (alternative.getId().equals(answersAndQuestionsDTO.getAlternativeID())) {
            answersAndQuestionsDTO.setCorrect(true);
        } else {
            answersAndQuestionsDTO.setCorrect(false);
        }

        return answersAndQuestionsDTO;
    }

    private UUID getStudentId(String email) {

        Optional<Student> student = studentRepository.getStudentByEmail(email);

        if (student.isEmpty()) {
            return UUID.randomUUID();
        } else {
            return student.get().getId();
        }
    }

    private Certification buildCertification(AnswersDTO answers) {

        UUID studentId = getStudentId(answers.getEmail());

        Certification certification = CertificationBuilder.from(studentId, answers);

        List<Answer> answerList = AnswerBuilder.from(certification.getStudentId(), certification.getId(), answers.getAnswersAndQuestions());

        certification.setAnswersCertification(answerList);

        return certification;
    }
}

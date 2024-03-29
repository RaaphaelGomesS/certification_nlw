package com.rarwin.certification_nlw.service;

import com.rarwin.certification_nlw.builder.AnswerBuilder;
import com.rarwin.certification_nlw.builder.CertificationBuilder;
import com.rarwin.certification_nlw.dto.AnswersAndQuestionsDTO;
import com.rarwin.certification_nlw.dto.AnswersDTO;
import com.rarwin.certification_nlw.entities.*;
import com.rarwin.certification_nlw.exception.StudentException;
import com.rarwin.certification_nlw.repository.CertificationRepository;
import com.rarwin.certification_nlw.repository.QuestionRepository;
import com.rarwin.certification_nlw.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AnswerService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CertificationRepository certificationRepository;

    @Autowired
    private CertificationService certificationService;

    @Autowired
    private StudentService studentService;

    public Certification checkAnswersFromStudent(AnswersDTO answers) throws StudentException {

        Student student = studentService.getStudentAndVerifyHasCertification(answers);

        List<AnswersAndQuestionsDTO> answersAndQuestions = answers.getAnswersAndQuestions();

        List<Question> questions = questionRepository.findByTech(answers.getTechnology());

        AtomicInteger correctAnswers = new AtomicInteger(0);

        for (AnswersAndQuestionsDTO answersAndQuestionsDTO : answersAndQuestions) {
            Question question = questions.stream().filter(questionEntity -> answersAndQuestionsDTO.getQuestionID().equals(questionEntity.getId())).findFirst().get();

            if (setAlternativeIsCorrect(answersAndQuestionsDTO, question.getAlternatives())) {
                correctAnswers.incrementAndGet();
            }
        }

        Certification newCertification = buildCertification(answers, student, correctAnswers);

        return certificationRepository.save(newCertification);
    }

    private boolean setAlternativeIsCorrect(AnswersAndQuestionsDTO answersAndQuestionsDTO, List<Alternative> alternatives) {

        Alternative alternative = alternatives.stream().filter(alternativeEntity -> alternativeEntity.isCorrect()).findFirst().get();

        if (alternative.getId().equals(answersAndQuestionsDTO.getAlternativeID())) {
            answersAndQuestionsDTO.setCorrect(true);
            return true;
        } else {
            answersAndQuestionsDTO.setCorrect(false);
            return false;
        }
    }

    private Certification buildCertification(AnswersDTO answers, Student student, AtomicInteger correctAnswers) {

        Certification certification = CertificationBuilder.from(student.getId(), answers);

        certificationRepository.save(certification);

        List<Answer> answerList = AnswerBuilder.from(student.getId(), certification.getId(), answers.getAnswersAndQuestions());

        certification.setAnswersCertification(answerList);
        certification.setGrade(correctAnswers.get());

        return certification;
    }
}

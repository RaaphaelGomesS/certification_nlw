package com.rarwin.certification_nlw.service;

import com.rarwin.certification_nlw.dto.AnswersAndQuestionsDTO;
import com.rarwin.certification_nlw.dto.AnswersDTO;
import com.rarwin.certification_nlw.entities.Alternative;
import com.rarwin.certification_nlw.entities.Question;
import com.rarwin.certification_nlw.entities.Student;
import com.rarwin.certification_nlw.exception.StudentException;
import com.rarwin.certification_nlw.repository.QuestionRepository;
import com.rarwin.certification_nlw.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private QuestionRepository questionRepository;

    public AnswersDTO checkAnswersFromStudent(AnswersDTO answers) {

        List<AnswersAndQuestionsDTO> answersAndQuestions = answers.getAnswersAndQuestions();

        List<Question> questions = questionRepository.findByTech(answers.getTechnology());

        answersAndQuestions.stream().forEach(answersAndQuestionsDTO -> {
            Question question = questions.stream().filter(questionEntity -> answersAndQuestionsDTO.getQuestionID().equals(questionEntity.getId())).findFirst().get();

            setAlternativeCorrect(answersAndQuestionsDTO, question.getAlternatives());
        });

        return answers;
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
}

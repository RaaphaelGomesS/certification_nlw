package com.rarwin.certification_nlw.controller;


import com.rarwin.certification_nlw.builder.QuestionResultDTOBuilder;
import com.rarwin.certification_nlw.dto.QuestionResultDTO;
import com.rarwin.certification_nlw.entities.Question;
import com.rarwin.certification_nlw.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/technology/{tech}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String tech) {

        List<Question> questions = questionRepository.findByTech(tech);

        return questions.stream().map(question -> QuestionResultDTOBuilder.from(question)).collect(Collectors.toList());
    }
}

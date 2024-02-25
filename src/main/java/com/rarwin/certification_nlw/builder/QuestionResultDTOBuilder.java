package com.rarwin.certification_nlw.builder;

import com.rarwin.certification_nlw.dto.QuestionResultDTO;
import com.rarwin.certification_nlw.entities.Question;

public class QuestionResultDTOBuilder {

    public static QuestionResultDTO from(Question question) {

        return question != null ? QuestionResultDTO.builder()
                .id(question.getId())
                .technology(question.getTech())
                .description(question.getDescription())
                .alternatives(AlternativeResultDTOBuilder.from(question.getAlternatives()))
                .build() : null;
    }

}

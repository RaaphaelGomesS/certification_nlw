package com.rarwin.certification_nlw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswersAndQuestionsDTO {

    private UUID alternativeID;

    private UUID questionID;

    private boolean correct;
}

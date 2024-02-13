package com.rarwin.certification_nlw.entities;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    private UUID certificationId;

    private UUID studentId;

    private UUID questionId;

    private UUID answerId;

    private boolean isCorrect;
}

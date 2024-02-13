package com.rarwin.certification_nlw.entities;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Alternative {

    private UUID id;

    private String description;

    private UUID questionId;

    private boolean isCorrect;
}

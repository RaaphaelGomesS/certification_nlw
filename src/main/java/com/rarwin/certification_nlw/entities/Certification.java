package com.rarwin.certification_nlw.entities;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Certification {

    private UUID id;

    private UUID studentId;

    private TechnologyIndicator tech;

    private int grade;
}

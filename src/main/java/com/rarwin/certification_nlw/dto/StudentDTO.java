package com.rarwin.certification_nlw.dto;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private String email;

    private String tech;
}

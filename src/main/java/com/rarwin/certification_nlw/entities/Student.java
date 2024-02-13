package com.rarwin.certification_nlw.entities;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private UUID id;

    private String email;

    private List<Certification> certificationList;
}

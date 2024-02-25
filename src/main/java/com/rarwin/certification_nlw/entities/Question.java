package com.rarwin.certification_nlw.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "technology")
    private String tech;

    @Column(name = "description")
    private String description;

    @OneToMany
    @JoinColumn(name = "question_id")
    private List<Alternative> alternatives;

    @CreationTimestamp
    private LocalDateTime createAt;
}

package com.rarwin.certification_nlw.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "certification_id")
    private UUID certificationId;

    @ManyToOne
    @JoinColumn(name = "certification_id", insertable = false, updatable = false)
    private Certification certification;

    @Column(name = "student_id")
    private UUID studentId;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @Column(name = "question_id")
    private UUID questionId;

    @Column(name = "answer_id")
    private UUID answerId;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @CreationTimestamp
    private LocalDateTime createAt;
}

package com.rarwin.certification_nlw.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "certifications")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "student_id")
    private UUID studentId;

    @Column(length = 10)
    private int grade;

    @Enumerated(EnumType.STRING)
    private TechnologyIndicator tech;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "certification_id")
    List<Answer> answersCertification;

    @CreationTimestamp
    private LocalDateTime createAt;
}

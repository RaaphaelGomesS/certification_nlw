package com.rarwin.certification_nlw.repository;

import com.rarwin.certification_nlw.entities.Certification;
import com.rarwin.certification_nlw.entities.TechnologyIndicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface CertificationRepository extends JpaRepository<Certification, UUID> {

    @Query("SELECT c FROM certifications c INNER JOIN c.student std WHERE std.email = :email AND c.tech = :tech")
    List<Certification> findByStudentEmailAndTechnology(String email, TechnologyIndicator tech);
}

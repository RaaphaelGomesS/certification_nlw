package com.rarwin.certification_nlw.repository;

import com.rarwin.certification_nlw.entities.Certification;
import com.rarwin.certification_nlw.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface StudentRepository extends JpaRepository<Student, UUID> {

    @Query("SELECT s FROM students s WHERE s.email = :email")
    Optional<Student> getStudentByEmail(@Param("email") String email);
}

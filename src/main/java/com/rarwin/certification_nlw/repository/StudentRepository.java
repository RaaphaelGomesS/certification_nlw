package com.rarwin.certification_nlw.repository;

import com.rarwin.certification_nlw.entities.Certification;
import com.rarwin.certification_nlw.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Certification, UUID> {

    @Query("SELECT * FROM students WHERE email = :email")
    Optional<Student> getStudentByEmail(String email);
}

package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Es un repository porque es responsable del acceso a los datos
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    //Esto es lo mismo que "SELECT * from STUDENT WHERE email = <email>
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}

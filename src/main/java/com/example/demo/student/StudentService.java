package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.lang.Object;

//Con esta annotation estamos diciendo que es un service
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        //El repository extiende de JPARepository y por eso tiene todas las implementaciones de JPA y nosotros no
        //tenemos por qué saber cómo hace todoo internamente.
        return studentRepository.findAll();
    }

    public Student addNewStudent (Student student){
//        System.out.println(student);
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()){
            throw new IllegalStateException("The user with that email already exists");
        }
        return studentRepository.save(student);
    }

    public String deleteStudent (Long studentId) {
        Boolean studentExists = studentRepository.existsById(studentId);
        if (studentExists) {
            studentRepository.deleteById(studentId);
            return "Deleted ok";
        } else {
            throw new IllegalStateException("Student with id " + studentId + " doesn't exist");
        }
    }

    @Transactional
    public String updateStudent (
            Long studentId,
            String name,
            String email,
            LocalDate dob
            ) {
        Student currentStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " doesn't exist"));

        if (name != null &&
            name.length() > 0 &&
            currentStudent.getName() != name){
            currentStudent.setName(name);
        }

        if (email != null &&
                email.length() > 0 &&
                currentStudent.getEmail() != email){
            currentStudent.setEmail(email);
        }

        if (dob != null &&
                currentStudent.getDob() != dob){
            currentStudent.setDob(dob);
        }
        return "Updated ok";
    }
}

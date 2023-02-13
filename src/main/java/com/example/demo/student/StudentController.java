package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//Todos los @ en spring boot son para services.
//Estamos diciendo que es un controller
@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService;

    //Autowired es para que StudentService haga referencia al servicio dentro del package (?
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    public Student signupStudent (@RequestBody Student student){
        Student studentSaved = studentService.addNewStudent(student);
        return studentSaved;
    }

    @DeleteMapping(path = "{studentId}")
    public String deleteStudent (@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return ("Student with id " + studentId + " was deleted");
    }

    @PutMapping(path = "{studentId}")
    public String updateStudent (
            @PathVariable("studentId") Long studentId,
            @RequestBody (required = false) Student student
    ){
        String message = studentService.updateStudent(studentId,student.getName(), student.getEmail(), student.getDob());
        return message;
    }
}

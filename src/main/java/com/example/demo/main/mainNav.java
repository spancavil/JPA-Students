package com.example.demo.main;

import com.example.demo.student.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class mainNav {
    @GetMapping
    public String getStudents(){
        return "Welcome to the app";
    }
}

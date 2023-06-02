package com.example.callapitask.controller;

import com.example.callapitask.model.Student;
import com.example.callapitask.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return this.studentService.postStudent(student);
    }

}

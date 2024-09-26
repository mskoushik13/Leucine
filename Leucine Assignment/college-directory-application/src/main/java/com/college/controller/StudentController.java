package com.college.controller;

import com.college.model.StudentProfile;
import com.college.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentProfile> getAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getStudentById(@PathVariable Long id) {
        return studentService.findStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public StudentProfile createStudent(@RequestBody StudentProfile studentProfile) {
        return studentService.saveStudent(studentProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentProfile> updateStudent(@PathVariable Long id, @RequestBody StudentProfile studentProfile) {
        return studentService.updateStudent(id, studentProfile)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (studentService.deleteStudent(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

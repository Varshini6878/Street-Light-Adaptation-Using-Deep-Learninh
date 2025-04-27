package com.example.StudentDetail1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @GetMapping
    public List<StudentInfo> getAllStudents() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public StudentInfo getStudent(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public StudentInfo addStudent(@RequestBody StudentInfo student) {
        return repository.save(student);
    }

    @PutMapping("/{id}")
    public StudentInfo updateStudent(@PathVariable Long id, @RequestBody StudentInfo studentData) {
        StudentInfo student = repository.findById(id).orElse(null);
        if (student != null) {
            student.setName(studentData.getName());
            student.setEmail(studentData.getEmail());
            return repository.save(student);
        }
        return null;
    }

    @PatchMapping("/{id}")
    public StudentInfo updateEmail(@PathVariable Long id, @RequestBody String email) {
        StudentInfo student = repository.findById(id).orElse(null);
        if (student != null) {
            student.setEmail(email);
            return repository.save(student);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
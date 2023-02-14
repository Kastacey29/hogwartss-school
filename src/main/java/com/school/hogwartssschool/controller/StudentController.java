package com.school.hogwartssschool.controller;

import com.school.hogwartssschool.model.Faculty;
import com.school.hogwartssschool.model.Student;
import com.school.hogwartssschool.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/all")
    public Integer findCountOfStudent() {
        return studentService.findCountOfStudent();
    }

    @GetMapping("/avgAge")
    public Double findAverageAge() {
        return studentService.findAverageAge();
    }

    @GetMapping("/fiveLast")
    public ResponseEntity<Collection<Student>> findFiveLastStudents() {
        return ResponseEntity.ok(studentService.findFiveLastStudents());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{studentId}/faculty")
    public ResponseEntity<Faculty> getFacultyByStudentId(@PathVariable Long studentId) {
        Faculty resultFaculty = studentService.getFacultyByStudentId(studentId);
        if (resultFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultFaculty);
    }

    @GetMapping("/all/{letter}")
    public ResponseEntity<Collection<String>> getAllOnLetter(@PathVariable Character letter) {
        Collection<String> students = studentService.getAllOnLetter(letter);
        return  ResponseEntity.ok(students);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getStudentsByAge(@RequestParam(required = false) Integer age,
                                                                @RequestParam(required = false) Integer minAge,
                                                                @RequestParam(required = false) Integer maxAge) {
        if (age != null) {
            return ResponseEntity.ok(studentService.getStudentsByAge(age));
        }
        if (minAge != null && maxAge != null && minAge <= maxAge) {
            return ResponseEntity.ok(studentService.findStudentsByAgeBetween(minAge, maxAge));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(student.getId(), student);
        if (updatedStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudentById(studentId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/byFaculty/{id}")
    public ResponseEntity<Collection<Student>> findStudentsByFacultyId(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findStudentsByFacultyId(id));
    }
}


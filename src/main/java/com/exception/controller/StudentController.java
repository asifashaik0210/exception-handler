package com.exception.controller;

import com.exception.entity.Student;
import com.exception.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student student1 = studentService.createStudent(student);
        return new ResponseEntity<>(student1, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long id) {
        studentService.deleteStudentById(id);
        return new ResponseEntity<>("record is deleted", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        Student studentById = studentService.getStudentById(id);
        return new ResponseEntity<>(studentById, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student student) {
        Student student1 = studentService.updateStudentById(id, student);
        return new ResponseEntity<>(student1, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Student>> getAll(@RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
                                                @RequestParam(name = "pageSize", required = false, defaultValue = "0") int pageSize,
                                                @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                                @RequestParam(name = "sortDir", required = false, defaultValue = "ASC") String sortDir) {

        List<Student> allStudents = studentService.getAllStudents(pageNo, pageSize,sortBy,sortDir);
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }
}
package com.example.Tugas3.controller;

import com.example.Tugas3.model.ApiResponse;
import com.example.Tugas3.model.Student;
import com.example.Tugas3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    
    @GetMapping("")
    public ResponseEntity getStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        "All list Student.",
                        studentService.getStudents()
                ));
    }

    @GetMapping("/{npm}")
    public ResponseEntity getStudentById(@PathVariable String npm) {
        if (studentService.getStudentByNpm(npm) != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            studentService.getMessage(),
                            studentService.getStudentByNpm(npm)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(studentService.getMessage()));
        }
    }

    @PostMapping("")
    public ResponseEntity addStudent(@RequestBody Student student) {
        if (studentService.add(student)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(
                            studentService.getMessage(),
                            studentService.getStudentByIndex(studentService.getStudentSize() - 1)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(studentService.getMessage()));
        }
    }

    @PutMapping("/{npm}")
    public ResponseEntity updateStudent(@PathVariable String npm, @RequestBody Student student) {
        if (studentService.update(npm, student)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            studentService.getMessage(),
                            studentService.getStudentByNpm(npm)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(studentService.getMessage()));
        }
    }

    @PatchMapping("/{npm}/activated")
    public ResponseEntity updateActivated(@PathVariable String npm, @RequestBody Student student) {
        if (studentService.updateActivated(npm, student)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            studentService.getMessage(),
                            studentService.getStudentByNpm(npm)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(studentService.getMessage()));
        }
    }

    @DeleteMapping("/{npm}")
    public ResponseEntity deleteStudent(@PathVariable String npm) {
        if (studentService.delete(npm)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            studentService.getMessage(),
                            studentService.getStudentByNpm(npm)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(studentService.getMessage()));
        }
    }
}

package com.example.Tugas3.Controller;

import com.example.Tugas3.Model.ApiResponse;
import com.example.Tugas3.Model.Student;
import com.example.Tugas3.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    
    @GetMapping("")
    public ResponseEntity getStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudents());
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

    @DeleteMapping("/{npm}")
    public ResponseEntity deleteStudent(@PathVariable String npm) {
        if (studentService.delete(npm)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(studentService.getMessage()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(studentService.getMessage()));
        }
    }
}

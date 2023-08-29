package com.example.Tugas3.Controller;

import com.example.Tugas3.Model.ApiResponse;
import com.example.Tugas3.Model.StudentCourse;
import com.example.Tugas3.Service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grades")
public class StudentCourseController {
    @Autowired
    private StudentCourseService studentCourseService;

    @GetMapping("")
    public ResponseEntity getStudentCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(studentCourseService.getStudentCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity getStudentCourseById(@PathVariable int id) {
        if (studentCourseService.getStudentCoursesById(id) != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            studentCourseService.getMessage(),
                            studentCourseService.getStudentCoursesById(id)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(studentCourseService.getMessage()));
        }
    }

    @PostMapping("")
    public ResponseEntity addStudentCourse(@RequestBody StudentCourse studentCourse) {
        if (studentCourseService.add(studentCourse)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(
                            studentCourseService.getMessage(),
                            studentCourseService.getStudentCoursesById(studentCourseService.getSize())
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(studentCourseService.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateStudentCourse(@PathVariable int id, @RequestBody StudentCourse studentCourse) {
        if (studentCourseService.update(id, studentCourse)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            studentCourseService.getMessage(),
                            studentCourseService.getStudentCoursesById(id)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(studentCourseService.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudentCourse(@PathVariable int id) {
        if (studentCourseService.delete(id)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(studentCourseService.getMessage()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(studentCourseService.getMessage()));
        }
    }
}

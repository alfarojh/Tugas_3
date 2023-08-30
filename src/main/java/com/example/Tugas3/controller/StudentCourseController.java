package com.example.Tugas3.controller;

import com.example.Tugas3.model.ApiResponse;
import com.example.Tugas3.model.StudentCourse;
import com.example.Tugas3.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student-course")
public class StudentCourseController {
    @Autowired
    private StudentCourseService studentCourseService;

    @GetMapping("")
    public ResponseEntity getStudentCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        "All list StudentCourse.",
                        studentCourseService.getStudentCourses()
                ));
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

    @PutMapping("/grade/{id}")
    public ResponseEntity updateGradeStudentCourse(@PathVariable int id, @RequestBody StudentCourse studentCourse) {
        if (studentCourseService.updateGrade(id, studentCourse)) {
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

    @PatchMapping("/{id}/activated")
    public ResponseEntity updateActiveStudentCourse(@PathVariable int id, @RequestBody StudentCourse studentCourse) {
        if (studentCourseService.updateActive(id, studentCourse)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            studentCourseService.getMessage(),
                            studentCourseService.getStudentCoursesById(id)));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(studentCourseService.getMessage()));
        }
    }
}

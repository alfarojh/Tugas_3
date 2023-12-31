package com.example.Tugas3.controller;

import com.example.Tugas3.model.ApiResponse;
import com.example.Tugas3.model.Course;
import com.example.Tugas3.service.CourseService;
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
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("")
    public ResponseEntity getCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        "All list Course.",
                        courseService.getCourses()
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity getCourseById(@PathVariable long id) {
        if (courseService.getCourseById(id) != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            courseService.getMessage(),
                            courseService.getCourseById(id)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(courseService.getMessage()));
        }
    }

    @PostMapping("")
    public ResponseEntity addCourse(@RequestBody Course course) {
        if (courseService.add(course)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(
                            courseService.getMessage(),
                            courseService.getCourseById(courseService.getCoursesSize())
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(courseService.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCourse(@PathVariable long id, @RequestBody Course course) {
        if (courseService.update(id,course)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            courseService.getMessage(),
                            courseService.getCourseById(id)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(courseService.getMessage()));
        }
    }

    @PatchMapping("/{id}/activated")
    public ResponseEntity updateActivated(@PathVariable long id, @RequestBody Course course) {
        if (courseService.updateActive(id,course)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            courseService.getMessage(),
                            courseService.getCourseById(id)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(courseService.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable long id) {
        if (courseService.delete(id)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            courseService.getMessage(),
                            courseService.getCourseById(id)));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(courseService.getMessage()));
        }
    }
}

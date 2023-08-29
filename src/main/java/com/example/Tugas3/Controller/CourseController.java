package com.example.Tugas3.Controller;

import com.example.Tugas3.Model.ApiResponse;
import com.example.Tugas3.Model.Course;
import com.example.Tugas3.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("")
    public ResponseEntity getCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCourseById(@PathVariable byte id) {
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
    public ResponseEntity updateCourse(@PathVariable byte id, @RequestBody Course course) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable byte id) {
        if (courseService.delete(id)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(courseService.getMessage()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(courseService.getMessage()));
        }
    }
}

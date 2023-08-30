package com.example.Tugas3.controller;

import com.example.Tugas3.model.ApiResponse;
import com.example.Tugas3.model.Major;
import com.example.Tugas3.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/majors")
public class MajorController {
    @Autowired
    private MajorService majorService;

    @GetMapping("")
    public ResponseEntity getMajors() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        "All list Major.",
                        majorService.getMajors()
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity getMajorById(@PathVariable long id) {
        if (majorService.getMajorById(id) != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            majorService.getMessage(),
                            majorService.getMajorById(id)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(majorService.getMessage()));
        }
    }

    @PostMapping("")
    public ResponseEntity addMajor(@RequestBody Major major) {
        if (majorService.add(major)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(
                            majorService.getMessage(),
                            majorService.getMajorById(majorService.getMajorsSize())
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(majorService.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMajor(@PathVariable long id, @RequestBody Major major) {
        if (majorService.update(id, major)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            majorService.getMessage(),
                            majorService.getMajorById(id)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(majorService.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMajor(@PathVariable long id) {
        if (majorService.delete(id)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            majorService.getMessage(),
                            majorService.getMajorById(id)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(majorService.getMessage()));
        }
    }
}

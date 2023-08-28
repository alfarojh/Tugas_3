package com.example.Tugas3.Controller;

import com.example.Tugas3.Model.ApiResponse;
import com.example.Tugas3.Model.Major;
import com.example.Tugas3.Service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/majors")
public class MajorController {
    @Autowired
    private MajorService majorService;

    @GetMapping("")
    public ResponseEntity getMajors() {
        return ResponseEntity.status(HttpStatus.OK).body(majorService.getMajors());
    }

    @GetMapping("/{id}")
    public ResponseEntity getMajorById(@PathVariable byte id) {
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
        if (majorService.add(major.getName())) {
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
    public ResponseEntity updateMajor(@PathVariable byte id, @RequestBody Major major) {
        if (majorService.update(id, major.getName())) {
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
    public ResponseEntity deleteMajor(@PathVariable byte id) {
        if (majorService.delete(id)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(majorService.getMessage()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(majorService.getMessage()));
        }
    }
}

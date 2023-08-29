package com.example.Tugas3.Service;

import com.example.Tugas3.Model.Course;
import com.example.Tugas3.Validation.InputValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private MajorService majorService;
    private InputValidation inputValidation = new InputValidation();
    private List<Course> courses = new ArrayList<>();
    private String message;

    public CourseService() {
        seed();
    }

    public boolean isIdExist(byte id) {
        if (getIndexById(id) < 0) {
            message = inputValidation.getMessage();
            return false;
        } else return true;
    }

    public String getMessage() {
        return message;
    }

    public byte getCoursesSize() {
        return (byte) courses.size();
    }

    //============================================== CRUD =================================================

    public boolean add(Course course) {
        if (inputValidation.isNameValid(course.getName()) &&
                inputValidation.isCreditValid(course.getCredit())) {
            courses.add(new Course(getNewId(), course.getName(), course.getCredit()));
            message = "Course added successfully.";
            return true;
        }
        message = inputValidation.getMessage();
        return false;
    }

    public boolean update(byte id, Course course) {
        if (inputValidation.isIdCourseValid(id) &&
                inputValidation.isNameValid(course.getName()) &&
                inputValidation.isCreditValid(course.getCredit()) &&
                isIdExist(id)) {
            courses.get(getIndexById(id)).setName(course.getName());
            courses.get(getIndexById(id)).setCredit(course.getCredit());
            courses.get(getIndexById(id)).setActive(course.isActive());
            message = "Course with ID `" + id + "` updated successfully.";
            return true;
        }
        message = inputValidation.getMessage();
        return false;
    }

    public boolean delete(byte id) {
        if (inputValidation.isIdCourseValid(id) && isIdExist(id)) {
            courses.get(getIndexById(id)).delete();
            message = "Course with ID `" + id + "` deleted successfully.";
            return true;
        }
        message = inputValidation.getMessage();
        return false;
    }

    public List<Course> getCourses() {
        return courses.stream()
                .filter(Course::isExist)
                .collect(Collectors.toList());
    }

    public Course getCourseById(byte id) {
        if (inputValidation.isIdMajorValid(id) && isIdExist(id)) {
            message = "Course ID Found.";
            return courses.get(getIndexById(id));
        }
        message = inputValidation.getMessage();
        return null;
    }

    //============================================== CRUD =================================================

    private byte getNewId() {
        return (byte) (courses.size() + 1);
    }

    private int getIndexById(byte id) {
        if (id > 0 && id <= courses.size() && courses.get(id - 1).isExist()) return id - 1;
        inputValidation.setMessage("Course ID not found.");
        return -1;
    }

    private void seed() {
        courses.add(new Course(getNewId(), "Kalkulus", (byte) 2));
        courses.add(new Course(getNewId(), "Matematika Diskrit", (byte) 2));
        courses.add(new Course(getNewId(), "Pancasila", (byte) 2));
    }
}

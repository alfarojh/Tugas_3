package com.example.Tugas3.Service;

import com.example.Tugas3.Model.StudentCourse;
import com.example.Tugas3.Validation.InputValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentCourseService {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    private List<StudentCourse> studentCourses = new ArrayList<>();
    private InputValidation inputValidation = new InputValidation();
    private String message;

    public StudentCourseService() {
        seed();
    }

    public String getMessage() {
        return message;
    }

    public int getSize() {
        return studentCourses.size();
    }

    public boolean isIdExist(int id) {
        if (getIndexById(id) < 0) {
            message = inputValidation.getMessage();
            return false;
        } else return true;
    }

    //============================================== CRUD =================================================

    public boolean add(String npm, byte idCourse) {
        if (inputValidation.isNpmValid(npm) &&
                inputValidation.isIdCourseValid(idCourse) &&
                isNpmExist(npm) && isIDCourseExist(idCourse) &&
                isCourseNotAlreadyTakenByNpm(getNewId(), npm, idCourse)) {
            studentCourses.add(new StudentCourse(getNewId(), npm, idCourse));
            message = "StudentCourse added successfully.";
            return true;
        }
        message = inputValidation.getMessage();
        return false;
    }

    public boolean update(int id, String npm, byte idCourse,
                               byte quiz1, byte quiz2, byte quiz3, byte quiz4, byte quiz5,
                               byte exam1, byte exam2) {
        if (inputValidation.isIdStudentCourseValid(id) && isIdExist(id) &&
                inputValidation.isNpmValid(npm) &&
                inputValidation.isIdCourseValid(idCourse) &&
                isNpmExist(npm) && isIDCourseExist(idCourse) &&
                isCourseNotAlreadyTakenByNpm(id, npm, idCourse) &&
                inputValidation.isGradeValid(quiz1, "quiz1") &&
                inputValidation.isGradeValid(quiz2, "quiz2") &&
                inputValidation.isGradeValid(quiz3, "quiz3") &&
                inputValidation.isGradeValid(quiz4, "quiz4") &&
                inputValidation.isGradeValid(quiz5, "quiz5") &&
                inputValidation.isGradeValid(exam1, "exam1") &&
                inputValidation.isGradeValid(exam2, "exam2")) {
            studentCourses.get(getIndexById(id)).setNpm(npm);
            studentCourses.get(getIndexById(id)).setIdCourse(idCourse);
            studentCourses.get(getIndexById(id)).setQuiz1(quiz1);
            studentCourses.get(getIndexById(id)).setQuiz2(quiz2);
            studentCourses.get(getIndexById(id)).setQuiz3(quiz3);
            studentCourses.get(getIndexById(id)).setQuiz4(quiz4);
            studentCourses.get(getIndexById(id)).setQuiz5(quiz5);
            studentCourses.get(getIndexById(id)).setExam1(exam1);
            studentCourses.get(getIndexById(id)).setExam2(exam2);
            message = "StudentCourse with ID `" + id + "` updated successfully.";
            return true;
        }
        message = inputValidation.getMessage();
        return false;
    }

    public boolean delete(int id) {
        if (inputValidation.isIdStudentCourseValid(id) && isIdExist(id)) {
            studentCourses.get(getIndexById(id)).delete();
            message = "StudentCourse with ID `" + id + "` deleted successfully.";
            return true;
        }
        message = inputValidation.getMessage();
        return false;
    }

    public List<StudentCourse> getStudentCourses() {
        return studentCourses.stream()
                .filter(StudentCourse::isExist)
                .collect(Collectors.toList());
    }

    public StudentCourse getStudentCoursesById(int id) {
        if (inputValidation.isIdStudentCourseValid(id) && isIdExist(id)) {
            message = "Student NPM Found.";
            return studentCourses.get(getIndexById(id));
        }
        message = inputValidation.getMessage();
        return null;
    }

    //============================================== CRUD =================================================

    private byte getNewId() {
        return (byte) (studentCourses.size() + 1);
    }

    private int getIndexById(int id) {
        if (id > 0 && id <= studentCourses.size() && studentCourses.get(id - 1).isExist()) return id - 1;
        inputValidation.setMessage("StudentCourse ID not found.");
        return -1;
    }

    private boolean isCourseNotAlreadyTakenByNpm(int id, String npm, byte idCourse) {
        for (StudentCourse studentCourse : studentCourses) {
            if (studentCourse.getId() != id && studentCourse.getNpm().equals(npm) &&
                    studentCourse.getIdCourse() == idCourse) {
                inputValidation.setMessage("The student with NPM `" + npm + "` has already taken course with id `" + idCourse + "`");
                return false;
            }
        }
        return true;
    }

    private boolean isNpmExist(String npm) {
        boolean isExist = studentService.isNpmExist(npm);
        inputValidation.setMessage(studentService.getMessage());
        return isExist;
    }

    private boolean isIDCourseExist(byte idCourse) {
        boolean isExist = courseService.isIdExist(idCourse);
        inputValidation.setMessage(courseService.getMessage());
        return isExist;
    }

    private void seed() {
        studentCourses.add(new StudentCourse(getNewId(), "2012010001", (byte) 1));
        studentCourses.add(new StudentCourse(getNewId(), "2012010001", (byte) 2));
        studentCourses.add(new StudentCourse(getNewId(), "2012010002", (byte) 1));
        studentCourses.add(new StudentCourse(getNewId(), "2012010002", (byte) 2));
    }
}

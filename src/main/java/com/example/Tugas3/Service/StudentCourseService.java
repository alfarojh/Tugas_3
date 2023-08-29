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

    public boolean add(StudentCourse studentCourse) {
        if (inputValidation.isNpmValid(studentCourse.getNpm()) &&
                inputValidation.isIdCourseValid(studentCourse.getIdCourse()) &&
                isNpmExist(studentCourse.getNpm()) && isIDCourseExist(studentCourse.getIdCourse()) &&
                isCourseNotAlreadyTakenByNpm(getNewId(), studentCourse.getNpm().trim(), studentCourse.getIdCourse())) {
            studentCourses.add(new StudentCourse(getNewId(), studentCourse.getNpm(), studentCourse.getIdCourse()));
            message = "StudentCourse added successfully.";
            return true;
        }
        message = inputValidation.getMessage();
        return false;
    }

    public boolean update(int id, StudentCourse studentCourse) {
        message = inputValidation.getMessage();
        if (inputValidation.isIdStudentCourseValid(id) && isIdExist(id) &&
                inputValidation.isNpmValid(studentCourse.getNpm()) &&
                inputValidation.isIdCourseValid(studentCourse.getIdCourse()) &&
                isNpmExist(studentCourse.getNpm()) && isIDCourseExist(studentCourse.getIdCourse()) &&
                isCourseNotAlreadyTakenByNpm(id, studentCourse.getNpm(), studentCourse.getIdCourse()) &&
                inputValidation.isGradeValid(studentCourse.getQuiz1(), "quiz1") &&
                inputValidation.isGradeValid(studentCourse.getQuiz2(), "quiz2") &&
                inputValidation.isGradeValid(studentCourse.getQuiz3(), "quiz3") &&
                inputValidation.isGradeValid(studentCourse.getQuiz4(), "quiz4") &&
                inputValidation.isGradeValid(studentCourse.getQuiz5(), "quiz5") &&
                inputValidation.isGradeValid(studentCourse.getExam1(), "exam1") &&
                inputValidation.isGradeValid(studentCourse.getExam2(), "exam2")) {
            studentCourses.get(getIndexById(id)).setNpm(studentCourse.getNpm());
            studentCourses.get(getIndexById(id)).setIdCourse(studentCourse.getIdCourse());
            studentCourses.get(getIndexById(id)).setQuiz1(studentCourse.getQuiz1());
            studentCourses.get(getIndexById(id)).setQuiz2(studentCourse.getQuiz2());
            studentCourses.get(getIndexById(id)).setQuiz3(studentCourse.getQuiz3());
            studentCourses.get(getIndexById(id)).setQuiz4(studentCourse.getQuiz4());
            studentCourses.get(getIndexById(id)).setQuiz5(studentCourse.getQuiz5());
            studentCourses.get(getIndexById(id)).setExam1(studentCourse.getExam1());
            studentCourses.get(getIndexById(id)).setExam2(studentCourse.getExam2());
            message = "StudentCourse with ID `" + id + "` updated successfully.";
            return true;
        }
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

package com.example.Tugas3.service;

import com.example.Tugas3.model.StudentCourse;
import com.example.Tugas3.validation.InputValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentCourseService {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    private static final List<StudentCourse> studentCourses = new ArrayList<>();
    private InputValidation inputValidation = new InputValidation();

    public StudentCourseService() {
        seed();
    }

    public String getMessage() {
        return inputValidation.getMessage();
    }

    /**
     * Mengembalikan jumlah objek Relasi Mahasiswa dan Mata Kuliah dalam daftar.
     *
     * @return      Jumlah objek Relasi Mahasiswa dan Mata Kuliah dalam daftar.
     */
    public boolean isIdExist(int id) {
        return getIndexById(id) >= 0;
    }

    /**
     * Mengembalikan jumlah objek Relasi Mahasiswa dan Mata Kuliah dalam daftar.
     *
     * @return      Jumlah objek Relasi Mahasiswa dan Mata Kuliah dalam daftar.
     */
    public int getSize() {
        return studentCourses.size();
    }

    //============================================== CRUD =================================================

    /**
     * Menambahkan objek Relasi Mahasiswa dan Mata Kuliah baru ke dalam daftar.
     *
     * @param studentCourse Objek Relasi Mahasiswa dan Mata Kuliah yang akan ditambahkan.
     * @return              True jika objek berhasil ditambahkan, dan false jika gagal.
     */
    public boolean add(StudentCourse studentCourse) {
        if (inputValidation.isNpmValid(studentCourse.getNpm()) &&
                inputValidation.isIdCourseValid(studentCourse.getIdCourse()) &&
                isNpmExist(studentCourse.getNpm()) &&
                isIDCourseExist(studentCourse.getIdCourse())) {

            studentCourses.add(new StudentCourse(
                    getNewId(),
                    studentCourse.getNpm(),
                    studentCourse.getIdCourse(),
                    courseService.getCreditById(studentCourse.getIdCourse())));
            inputValidation.setMessage("StudentCourse added successfully.");
            return true;
        }
        return false;
    }

    /**
     * Memperbarui informasi objek Relasi Mahasiswa dan Mata Kuliah yang ada
     * dalam daftar berdasarkan ID Relasi Mahasiswa dan Mata Kuliah.
     *
     * @param id            ID Relasi Mahasiswa dan Mata Kuliah yang akan diperbarui.
     * @param studentCourse Objek Relasi Mahasiswa dan Mata Kuliah yang ingin diperbarui.
     * @return              True jika objek berhasil diperbarui, dan false jika gagal.
     */
    public boolean updateGrade(int id, StudentCourse studentCourse) {
        if (inputValidation.isIdStudentCourseValid(id) &&
                isIdExist(id) &&
                inputValidation.isGradeValid(studentCourse.getQuiz1(), "quiz1") &&
                inputValidation.isGradeValid(studentCourse.getQuiz2(), "quiz2") &&
                inputValidation.isGradeValid(studentCourse.getQuiz3(), "quiz3") &&
                inputValidation.isGradeValid(studentCourse.getQuiz4(), "quiz4") &&
                inputValidation.isGradeValid(studentCourse.getQuiz5(), "quiz5") &&
                inputValidation.isGradeValid(studentCourse.getExam1(), "exam1") &&
                inputValidation.isGradeValid(studentCourse.getExam2(), "exam2")) {
            studentCourses.get(getIndexById(id)).setQuiz1(studentCourse.getQuiz1());
            studentCourses.get(getIndexById(id)).setQuiz2(studentCourse.getQuiz2());
            studentCourses.get(getIndexById(id)).setQuiz3(studentCourse.getQuiz3());
            studentCourses.get(getIndexById(id)).setQuiz4(studentCourse.getQuiz4());
            studentCourses.get(getIndexById(id)).setQuiz5(studentCourse.getQuiz5());
            studentCourses.get(getIndexById(id)).setExam1(studentCourse.getExam1());
            studentCourses.get(getIndexById(id)).setExam2(studentCourse.getExam2());
            inputValidation.setMessage("StudentCourse with ID `" + id + "` updated successfully.");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Mengu objek Relasi Mahasiswa dan Mata Kuliah dari dalam daftar.
     *
     * @param id    ID Relasi Mahasiswa dan Mata Kuliah yang akan dihapus.
     * @return      True jika objek berhasil dihapus, dan false jika gagal.
     */
    public boolean updateActive(int id, StudentCourse studentCourse) {
        if (inputValidation.isIdStudentCourseValid(id)) {
            studentCourses.get(getIndexById(id)).setActive(studentCourse.isActive());
            if (studentCourse.isActive()) {
                inputValidation.setMessage("StudentCourse ID `" + id + "` is now active.");
            } else {
                inputValidation.setMessage("StudentCourse ID `" + id + "` is now inactive.");
            }
            return true;
        }
        return false;
    }

    /**
     * Mengembalikan daftar Relasi Mahasiswa dan Mata Kuliah yang masih tersedia.
     *
     * @return      Daftar Relasi Mahasiswa dan Mata Kuliah yang masih tersedia.
     */
    public List<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    /**
     * Mengembalikan objek Relasi Mahasiswa dan Mata Kuliah yang masih tersedia
     * berdasarkan ID Relasi Mahasiswa dan Mata Kuliah.
     *
     * @param id    ID Relasi Mahasiswa dan Mata Kuliah.
     * @return      Relasi Mahasiswa dan Mata Kuliah yang masih tersedia.
     */
    public StudentCourse getStudentCoursesById(int id) {
        if (inputValidation.isIdStudentCourseValid(id) && isIdExist(id)) {
            inputValidation.setMessage("Student NPM Found.");
            return studentCourses.get(getIndexById(id));
        }
        return null;
    }

    //============================================== CRUD =================================================

    /**
     * Membuat ID baru berdasarkan jumlah daftar Relasi Mahasiswa dan Mata Kuliah ditambah 1.
     *
     * @return      ID Relasi Mahasiswa dan Mata Kuliah baru.
     */
    private long getNewId() {
        return studentCourses.size() + 1;
    }

    /**
     * Mendapatkan indeks dari daftar berdasarkan ID Relasi Mahasiswa dan Mata Kuliah.
     *
     * @return      Indeks daftar jika ID Relasi Mahasiswa dan Mata Kuliah ditemukan, -1 jika tidak ditemukan.
     */
    private int getIndexById(long id) {
        if (id > 0 && id <= studentCourses.size()) {
            return (int) (id - 1);
        }
        inputValidation.setMessage("StudentCourse id not found.");
        return -1;
    }

    /**
     * Memeriksa apakah NPM Mahasiswa ada.
     *
     * @param npm   NPM Mahasiswa.
     * @return      True jika NPM Mahasiswa ada, false jika sebaliknya.
     */
    private boolean isNpmExist(String npm) {
        boolean isExist = studentService.isNpmExist(npm);
        inputValidation.setMessage(studentService.getMessage());
        return isExist;
    }

    /**
     * Memeriksa apakah ID Mata Kuliah ada.
     *
     * @param idCourse   ID Jurusan.
     * @return          True jika ID Jurusan ada, false jika sebaliknya.
     */
    private boolean isIDCourseExist(long idCourse) {
        boolean isExist = courseService.isIdExist(idCourse);
        inputValidation.setMessage(courseService.getMessage());
        return isExist;
    }

    // Membuat daftar objek Mahasiswa baru secara default.
    private void seed() {
        studentCourses.add(new StudentCourse(getNewId(), "2012010001", 1, 2));
        studentCourses.add(new StudentCourse(getNewId(), "2012010001", 2, 4));
        studentCourses.add(new StudentCourse(getNewId(), "2012010002", 1, 2));
        studentCourses.add(new StudentCourse(getNewId(), "2012010002", 2, 4));
    }
}

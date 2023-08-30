package com.example.Tugas3.service;

import com.example.Tugas3.model.Course;
import com.example.Tugas3.validation.InputValidation;
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
    private static final List<Course> courses = new ArrayList<>();   // Daftar objek Mata Kuliah.

    public CourseService() {
        seed();
    }

    public String getMessage() {
        return inputValidation.getMessage();
    }

    /**
     * Memeriksa apakah ID Mata Kuliah ada.
     *
     * @param id    ID Mata Kuliah.
     * @return      True jika ID Mata Kuliah ada, false jika sebaliknya.
     */
    public boolean isIdExist(long id) {
        return getIndexById(id) >= 0;
    }

    /**
     * Mengembalikan jumlah objek Mata Kuliah dalam daftar.
     *
     * @return      Jumlah objek Mata Kuliah dalam daftar.
     */
    public int getCoursesSize() {
        return courses.size();
    }

    /**
     * Mengembalikan jumlah SKS Mata Kuliah dari dalam daftar.
     *
     * @return      Jumlah SKS Mata Kuliah.
     */
    public int getCreditById(long id) {
        return courses.get(getIndexById(id)).getCredit();
    }
    //============================================== CRUD =================================================

    /**
     * Menambahkan objek Mata Kuliah baru ke dalam daftar.
     *
     * @param course    Objek Mata Kuliah yang akan ditambahkan.
     * @return          True jika objek berhasil ditambahkan, dan false jika gagal.
     */
    public boolean add(Course course) {
        if (inputValidation.isNameValid(course.getName()) &&
                inputValidation.isCreditValid(course.getCredit())) {
            courses.add(new Course(getNewId(), course.getName(), course.getCredit()));
            inputValidation.setMessage("Course added successfully.");
            return true;
        }
        return false;
    }

    /**
     * Memperbarui informasi objek Mata Kuliah yang ada dalam daftar berdasarkan ID Mata Kuliah.
     *
     * @param id        ID Mata Kuliah yang akan diperbarui.
     * @param course    Objek Mata Kuliah yang ingin diperbarui.
     * @return          True jika objek berhasil diperbarui, dan false jika gagal.
     */
    public boolean update(long id, Course course) {
        if (inputValidation.isIdCourseValid(id) &&
                inputValidation.isNameValid(course.getName()) &&
                inputValidation.isCreditValid(course.getCredit()) &&
                isIdExist(id)) {
            courses.get(getIndexById(id)).setName(course.getName());
            courses.get(getIndexById(id)).setCredit(course.getCredit());
            inputValidation.setMessage("Course with ID `" + id + "` updated successfully.");
            return true;
        }
        return false;
    }

    public boolean updateActive(long id, Course course) {
        if (inputValidation.isIdCourseValid(id) && isIdExist(id)) {
            courses.get(getIndexById(id)).setActive(course.isActive());
            if (course.isActive()) {
                inputValidation.setMessage("Course ID `" + id + "` is now active.");
            } else {
                inputValidation.setMessage("Course ID `" + id + "` is now inactive.");
            }
            return true;
        }
        return false;
    }

    /**
     * Menghapus objek Mata Kuliah dari dalam daftar.
     *
     * @param id    ID Mata Kuliah yang akan dihapus.
     * @return      True jika objek berhasil dihapus, dan false jika gagal.
     */
    public boolean delete(long id) {
        if (inputValidation.isIdCourseValid(id) && isIdExist(id)) {
            courses.get(getIndexById(id)).delete();
            inputValidation.setMessage("Course with ID `" + id + "` deleted successfully.");
            return true;
        }
        return false;
    }

    /**
     * Mengembalikan daftar Mata Kuliah yang masih tersedia.
     *
     * @return      Daftar Jurusan yang masih tersedia.
     */
    public List<Course> getCourses() {
        return courses.stream()
                .filter(Course::isExist)
                .collect(Collectors.toList());
    }

    /**
     * Mengembalikan objek Mata Kuliah yang masih tersedia berdasarkan ID Mata Kuliah.
     *
     * @param id    ID Mata Kuliah.
     * @return      Mata Kuliah yang masih tersedia.
     */
    public Course getCourseById(long id) {
        if (inputValidation.isIdMajorValid(id) && isIdExist(id)) {
            inputValidation.setMessage("Course ID Found.");
            return courses.get(getIndexById(id));
        }
        return null;
    }

    //============================================== CRUD =================================================

    /**
     * Membuat ID baru berdasarkan jumlah daftar Mata Kuliah ditambah 1.
     *
     * @return      ID Mata Kuliah baru.
     */
    private long getNewId() {
        return courses.size() + 1;
    }

    /**
     * Mendapatkan indeks dari daftar berdasarkan ID Mata Kuliah.
     *
     * @return      Indeks daftar jika ID Mata Kuliah ditemukan, -1 jika tidak ditemukan.
     */
    private int getIndexById(long id) {
        if (id > 0 && id <= courses.size() && courses.get((int) (id-1)).isExist()) {
            return (int) (id - 1);
        }
        inputValidation.setMessage("Course id not found.");
        return -1;
    }

    // Membuat daftar objek Mata Kuliah baru secara default.
    private void seed() {
        courses.add(new Course(getNewId(), "Kalkulus", (byte) 2));
        courses.add(new Course(getNewId(), "Matematika Diskrit", (byte) 2));
        courses.add(new Course(getNewId(), "Pancasila", (byte) 2));
    }
}

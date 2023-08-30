package com.example.Tugas3.service;

import com.example.Tugas3.model.Student;
import com.example.Tugas3.validation.InputValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private MajorService majorService;
    private InputValidation inputValidation = new InputValidation();
    private static final List<Student> students = new ArrayList<>(); // Daftar objek Mahasiswa.

    public StudentService() {
        seed();
    }

    public String getMessage() {
        return inputValidation.getMessage();
    }

    /**
     * Memeriksa apakah NPM Mahasiswa ada.
     *
     * @param npm   NPM Mahasiswa.
     * @return      True jika NPM Mahasiswa ada, false jika sebaliknya.
     */
    public boolean isNpmExist(String npm) {
        return getIndexByNpm(npm) >= 0;
    }

    /**
     * Mengembalikan jumlah objek Mahasiswa dalam daftar.
     *
     * @return      Jumlah objek Mahasiswa dalam daftar.
     */
    public int getStudentSize() {
        return students.size();
    }

    /**
     * Mengembalikan objek Mahasiswa yang ada.
     * Jika indeks di atas sama dengan 0 dan di bawah jumlah objek Mahasiswa dalam daftar,
     * maka mengembalikan objek Mahasiswa.
     *
     * @param index   Indeks untuk objek Mahasiswa yang ingin diambil.
     * @return      Objek Mahasiswa jika indeks sesuai dan ada, null jika indeks tidak sesuai.
     */
    public Student getStudentByIndex(int index) {
        if (index >= 0 &&
                index < students.size() &&
                students.get(index).isExist()) {
            return students.get(index);
        }
        return null;
    }

    //============================================== CRUD =================================================

    /**
     * Menambahkan objek Mahasiswa baru ke dalam daftar.
     *
     * @param student   Objek Mahasiswa yang akan ditambahkan.
     * @return          True jika objek berhasil ditambahkan, dan false jika gagal.
     */
    public boolean add(Student student) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        int year = Integer.parseInt(format.format(new Timestamp(System.currentTimeMillis())));
        if (inputValidation.isIdMajorValid(student.getIdMajor()) &&
                isIDMajorExist(student.getIdMajor()) &&
                inputValidation.isNameValid(student.getName()) &&
                inputValidation.isGenderValid(student.getGender()) &&
                inputValidation.isAddressValid(student.getAddress()) &&
                inputValidation.isPhoneNumberValid(student.getPhoneNumber())) {
            students.add(new Student(
                    getNewNPM(year, student.getIdMajor()),
                    year,
                    student.getIdMajor(),
                    student.getName(),
                    student.getGender(),
                    student.getAddress(),
                    student.getPhoneNumber()));
            inputValidation.setMessage("Student added successfully.");
            return true;
        }
        return false;
    }

    /**
     * Memperbarui informasi objek Mahasiswa yang ada dalam daftar berdasarkan NPM Mahasiswa.
     *
     * @param npm       NPM Mahasiswa yang akan diperbarui.
     * @param student   Objek Mahasiswa yang ingin diperbarui.
     * @return          True jika objek berhasil diperbarui, dan false jika gagal.
     */
    public boolean update(String npm, Student student) {
        if (inputValidation.isNpmValid(npm) &&
                isNpmExist(npm) &&
                inputValidation.isIdMajorValid(student.getIdMajor()) &&
                majorService.isIdExist(student.getIdMajor()) &&
                inputValidation.isNameValid(student.getName()) &&
                inputValidation.isGenderValid(student.getGender()) &&
                inputValidation.isAddressValid(student.getAddress()) &&
                inputValidation.isPhoneNumberValid(student.getPhoneNumber())) {
            students.get(getIndexByNpm(npm)).setName(student.getName());
            students.get(getIndexByNpm(npm)).setGender(student.getGender());
            students.get(getIndexByNpm(npm)).setAddress(student.getAddress());
            students.get(getIndexByNpm(npm)).setPhoneNumber(student.getPhoneNumber());
            inputValidation.setMessage("Student with NPM `" + npm + "` updated successfully.");
            return true;
        }
        return false;
    }

    public boolean updateActivated(String npm, Student student) {
        if (inputValidation.isNpmValid(npm) && isNpmExist(npm)) {
            students.get(getIndexByNpm(npm)).setActive(student.isActive());
            if (student.isActive()) {
                inputValidation.setMessage("Student NPM `" + npm + "` is now active.");
            } else {
                inputValidation.setMessage("Student NPM `" + npm + "` is now inactive.");
            }
            return true;
        }
        return false;
    }

    /**
     * Menghapus objek Mahasiswa dari dalam daftar.
     *
     * @param npm   NPM Mahasiswa yang akan dihapus.
     * @return      True jika objek berhasil dihapus, dan false jika gagal.
     */
    public boolean delete(String npm) {
        if (inputValidation.isNpmValid(npm) && isNpmExist(npm)) {
            students.get(getIndexByNpm(npm)).delete();
            inputValidation.setMessage("Student with NPM `" + npm + "` deleted successfully.");
            return true;
        }
        return false;
    }

    /**
     * Mengembalikan daftar Mahasiswa yang masih tersedia.
     *
     * @return      Daftar Mahasiswa yang masih tersedia.
     */
    public List<Student> getStudents() {
        return students.stream()
                .filter(Student::isExist)
                .collect(Collectors.toList());
    }

    /**
     * Mengembalikan objek Mahasiswa yang masih tersedia berdasarkan ID Jurusan.
     *
     * @param npm   NPM Mahasiswa.
     * @return      Jurusan yang masih tersedia.
     */
    public Student getStudentByNpm(String npm) {
        if (inputValidation.isNpmValid(npm) && isNpmExist(npm)) {
            inputValidation.setMessage("Student NPM Found.");
            return students.get(getIndexByNpm(npm));
        }
        return null;
    }

    //============================================== CRUD =================================================

    /**
     * Membuat NPM baru berdasarkan tahun, id Jurusan, dan jumlah.
     *
     * @return      NPM Mahasiswa baru.
     */
    private String getNewNPM(int year, long idMajor) {
        int count = 1;
        for (int indexStudent = students.size() - 1; indexStudent >= 0 ; indexStudent--) {
            if (students.get(indexStudent).getNpm().substring(0, 6).equals(year + String.format("%02d", idMajor))) {
                count = Integer.parseInt(students.get(indexStudent).getNpm().substring(6, 10)) + 1;
            }
        }
        return String.format("%d%02d%04d", year, idMajor, count);
    }

    /**
     * Mendapatkan indeks dari daftar berdasarkan NPM Mahasiswa.
     *
     * @return      Indeks daftar jika NPM Mahasiswa ditemukan, -1 jika tidak ditemukan.
     */
    private int getIndexByNpm(String npm) {
        for (int indexStudent = 0; indexStudent < students.size(); indexStudent++) {
            if (students.get(indexStudent).getNpm().equals(npm) &&
                    students.get(indexStudent).isExist()) {
                return indexStudent;
            }
        }
        inputValidation.setMessage("NPM not found.");
        return -1;
    }

    /**
     * Memeriksa apakah ID Jurusan ada.
     *
     * @param idMajor   ID Jurusan.
     * @return          True jika ID Jurusan ada, false jika sebaliknya.
     */
    private boolean isIDMajorExist(long idMajor) {
        boolean isExist = majorService.isIdExist(idMajor);
        inputValidation.setMessage(majorService.getMessage());
        return isExist;
    }

    // Membuat daftar objek Mahasiswa baru secara default.
    private void seed() {
        long idMajor = 1;
        int year = 2012;
        students.add(new Student(
                getNewNPM(year, idMajor),
                year,
                idMajor,
                "Budi",
                "Laki-laki",
                "Bandung",
                "08231238438"));
        students.add(new Student(
                getNewNPM(year, idMajor),
                year,
                idMajor,
                "Santi",
                "Perempuan",
                "Bandung",
                "085748372934"));
    }
}

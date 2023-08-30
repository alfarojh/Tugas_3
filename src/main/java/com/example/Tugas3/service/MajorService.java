package com.example.Tugas3.service;

import com.example.Tugas3.model.Major;
import com.example.Tugas3.validation.InputValidation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MajorService {
    private InputValidation inputValidation = new InputValidation();
    private static final List<Major> majors = new ArrayList<>(); // Daftar objek Jurusan

    public MajorService() {
        seed();
    }

    public String getMessage() {
        return inputValidation.getMessage();
    }

    /**
     * Memeriksa apakah ID Jurusan ada.
     *
     * @param id    ID Jurusan.
     * @return      True jika ID Jurusan ada, false jika sebaliknya.
     */
    public boolean isIdExist(long id) {
        return getIndexById(id) >= 0;
    }

    /**
     * Mengembalikan jumlah objek Jurusan dalam daftar.
     *
     * @return      Jumlah objek Jurusan dalam daftar.
     */
    public int getMajorsSize() {
        return majors.size();
    }

    //============================================== CRUD =================================================

    /**
     * Menambahkan objek Jurusan baru ke dalam daftar.
     *
     * @param major Objek Jurusan yang akan ditambahkan.
     * @return      True jika objek berhasil ditambahkan, dan false jika gagal.
     */
    public boolean add(Major major) {
        if (inputValidation.isNameValid(major.getName())) {
            majors.add(new Major(getNewID(), major.getName()));
            inputValidation.setMessage("Major added successfully.");
            return true;
        }
        return false;
    }

    /**
     * Memperbarui informasi objek Jurusan yang ada dalam daftar berdasarkan ID Jurusan.
     *
     * @param id    ID Jurusan yang akan diperbarui.
     * @param major Objek jurusan yang ingin diperbarui.
     * @return      True jika objek berhasil diperbarui, dan false jika gagal.
     */
    public boolean update(long id, Major major) {
        if (inputValidation.isIdMajorValid(id) &&
                inputValidation.isNameValid(major.getName()) &&
                isIdExist(id)) {
            majors.get(getIndexById(id)).setName(major.getName());
            inputValidation.setMessage("Major with ID `" + id + "` updated successfully.");
            return true;
        }
        return false;
    }

    /**
     * Menghapus objek Jurusan dari dalam daftar.
     *
     * @param id    ID Jurusan yang akan dihapus.
     * @return      True jika objek berhasil dihapus, dan false jika gagal.
     */
    public boolean delete(long id) {
        if (inputValidation.isIdMajorValid(id) &&
                isIdExist(id)) {
            majors.get(getIndexById(id)).delete();
            inputValidation.setMessage("Major with ID `" + id + "` deleted successfully.");
            return true;
        }
        return false;
    }

    /**
     * Mengembalikan daftar Jurusan yang masih tersedia.
     *
     * @return      Daftar Jurusan yang masih tersedia.
     */
    public List<Major> getMajors() {
        return majors.stream()
                .filter(Major::isExist)
                .collect(Collectors.toList());
    }

    /**
     * Mengembalikan objek Jurusan yang masih tersedia berdasarkan ID Jurusan.
     *
     * @param id    ID Jurusan.
     * @return      Jurusan yang masih tersedia.
     */
    public Major getMajorById(long id) {
        if (inputValidation.isIdMajorValid(id) && isIdExist(id)) {
            inputValidation.setMessage("Major ID Found.");
            return majors.get(getIndexById(id));
        }
        return null;
    }

    //============================================== CRUD =================================================

    /**
     * Membuat ID baru berdasarkan jumlah daftar Jurusan ditambah 1.
     *
     * @return      ID Jurusan baru.
     */
    private long getNewID() {
        return majors.size() + 1;
    }

    /**
     * Mendapatkan indeks dari daftar berdasarkan ID Jurusan.
     *
     * @return      Indeks daftar jika ID Jurusan ditemukan, -1 jika tidak ditemukan.
     */
    private int getIndexById(long id) {
        if (id > 0 && id <= majors.size() && majors.get((int) (id-1)).isExist()) {
            return (int) (id - 1);
        }
        inputValidation.setMessage("Major id not found.");
        return -1;
    }

    // Membuat daftar objek Jurusan baru secara default.
    private void seed() {
        majors.add(new Major(getNewID(),"Teknik Informatika"));
        majors.add(new Major(getNewID(),"Sistem Informasi"));
    }
}

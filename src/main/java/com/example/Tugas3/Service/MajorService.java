package com.example.Tugas3.Service;

import com.example.Tugas3.Model.Major;
import com.example.Tugas3.Validation.InputValidation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MajorService {
    private InputValidation inputValidation = new InputValidation();
    private List<Major> majors = new ArrayList<>(); // Daftar objek Jurusan
    private String message;                         // Tempat penampungan pesan.

    public MajorService() {
        seed();
    }

    public String getMessage() {
        return message;
    }

    /**
     * Memeriksa apakah ID Jurusan ada.
     *
     * @param id    ID Jurusan.
     * @return      True jika ID Jurusan ada, false jika sebaliknya.
     */
    public boolean isIdExist(byte id) {
        if (getIndexById(id) < 0) {
            message = inputValidation.getMessage();
            return false;
        }
        return true;
    }

    /**
     * Mengembalikan jumlah objek Jurusan dalam daftar.
     *
     * @return      Jumlah objek Jurusan dalam daftar.
     */
    public byte getMajorsSize() {
        return (byte) majors.size();
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
            majors.add(new Major(getNewID(), major.getName().trim()));
            message = "Major added successfully.";
            return true;
        }
        message = inputValidation.getMessage();
        return false;
    }

    /**
     * Memperbarui informasi objek Jurusan yang ada dalam daftar berdasarkan ID Jurusan.
     *
     * @param id    ID Jurusan yang akan diperbarui.
     * @param major Objek jurusan yang ingin diperbarui.
     * @return      True jika objek berhasil diperbarui, dan false jika gagal.
     */
    public boolean update(byte id, Major major) {
        if (inputValidation.isIdMajorValid(id) &&
                inputValidation.isNameValid(major.getName()) &&
                isIdExist(id)) {
            majors.get(getIndexById(id)).setName(major.getName());
            message = "Major with ID `" + id + "` updated successfully.";
            return true;
        }
        message = inputValidation.getMessage();
        return false;
    }

    /**
     * Menghapus objek Jurusan dari dalam daftar.
     *
     * @param id    ID Jurusan yang akan dihapus.
     * @return      True jika objek berhasil dihapus, dan false jika gagal.
     */
    public boolean delete(byte id) {
        if (inputValidation.isIdMajorValid(id) &&
                isIdExist(id)) {
            majors.get(getIndexById(id)).delete();
            message = "Major with ID `" + id + "` deleted successfully.";
            return true;
        }
        message = inputValidation.getMessage();
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
    public Major getMajorById(byte id) {
        if (inputValidation.isIdMajorValid(id) && isIdExist(id)) {
            message = "Major ID Found.";
            return majors.get(getIndexById(id));
        }
        message = inputValidation.getMessage();
        return null;
    }

    //============================================== CRUD =================================================

    /**
     * Membuat ID baru berdasarkan jumlah daftar Jurusan ditambah 1.
     *
     * @return      ID Jurusan baru.
     */
    private byte getNewID() {
        return (byte) (majors.size() + 1);
    }

    /**
     * Mendapatkan indeks dari daftar berdasarkan ID Jurusan.
     *
     * @return      Indeks daftar jika ID Jurusan ditemukan, -1 jika tidak ditemukan.
     */
    private int getIndexById(byte id) {
        if (id > 0 && id <= majors.size()) {
            if (majors.get(id-1).isExist()) return id - 1;
            else inputValidation.setMessage("Major id has been deleted.");
        } else {
            inputValidation.setMessage("Major id not found.");
        }

        return -1;
    }

    // Membuat daftar objek Jurusan baru secara default.
    private void seed() {
        majors.add(new Major(getNewID(),"Teknik Informatika"));
        majors.add(new Major(getNewID(),"Sistem Informasi"));
    }
}

package com.example.Tugas3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Student {
    private final String npm;   // NPM Mahasiswa
    private String name;        // Nama Mahasiswa
    private byte idMajor;       // Jurusan yang diambil Mahasiswa
    private String gender;      // Jenis kelamin Mahasiswa
    private String address;     // Alamat Mahasiswa
    private String phoneNumber; // Nomor Telepon Mahasiswa
    private boolean isActive;   // Status aktif Mahasiswa
    private boolean isDeleted;  // Status penghapusan Mahasiswa

    public Student(String npm, byte idMajor, String name, String gender, String address, String phoneNumber) {
        this.npm = npm;
        this.idMajor = idMajor;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isActive = true;
        this.isDeleted = false;
    }

    public String getNpm() {
        return npm.trim();
    }

    public String getName() {
        return name.trim();
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public byte getIdMajor() {
        return idMajor;
    }

    public void setIdMajor(byte idMajor) {
        this.idMajor = idMajor;
    }

    public String getGender() {
        return gender.trim();
    }

    public void setGender(String gender) {
        this.gender = gender.trim();
    }

    public String getAddress() {
        return address.trim();
    }

    public void setAddress(String address) {
        this.address = address.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber.trim();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber.trim();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @JsonIgnore
    public boolean isExist() {
        return !isDeleted;
    }

    public void delete() {
        isDeleted = true;
    }
}

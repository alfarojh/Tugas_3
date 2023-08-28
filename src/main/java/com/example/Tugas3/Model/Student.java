package com.example.Tugas3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Student {
    private final String npm;
    private String name;
    private byte idMajor;
    private String gender;
    private String address;
    private String phoneNumber;
    private boolean isActive;
    private boolean isDeleted;

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
        return npm;
    }

    public String getName() {
        return name;
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
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

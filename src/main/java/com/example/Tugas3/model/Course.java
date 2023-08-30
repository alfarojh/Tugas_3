package com.example.Tugas3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Course {
    private final long id;      // ID Mata Kuliah
    private String name;        // Nama Mata Kuliah
    private Byte credit;        // Jumlah SKS
    private boolean isActive;   // Status aktif Mata Kuliah
    private boolean isDeleted;  // Status penghapusan Mata Kuliah

    public Course(long id, String name, Byte credit) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.isActive = true;
        this.isDeleted = false;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        if (name == null) return null;
        return name.trim();
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public Byte getCredit() {
        return credit;
    }

    public void setCredit(Byte credit) {
        this.credit = credit;
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

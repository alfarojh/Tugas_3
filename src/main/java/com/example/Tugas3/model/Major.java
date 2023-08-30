package com.example.Tugas3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Major {
    private final long id;      // ID Jurusan
    private String name;        // Nama Jurusan
    private boolean isDeleted;  // Status terhapusnya Jurusan

    public Major(long id, String name) {
        this.id = id;
        this.name = name;
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

    @JsonIgnore
    public boolean isExist() {
        return !isDeleted;
    }

    public void delete() {
        isDeleted = true;
    }
}

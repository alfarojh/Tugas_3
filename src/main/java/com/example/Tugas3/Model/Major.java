package com.example.Tugas3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Major {
    private final byte id;      // ID Jurusan
    private String name;        // Nama Jurusan
    private boolean isDeleted;  // Status terhapusnya Jurusan

    public Major(byte id, String name) {
        this.id = id;
        this.name = name;
        this.isDeleted = false;
    }

    public byte getId() {
        return id;
    }

    public String getName() {
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

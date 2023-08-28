package com.example.Tugas3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Course {
    private final byte id;
    private String name;
    private byte credit;
    private boolean isActive;
    private boolean isDeleted;

    public Course(byte id, String name, byte credit) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.isActive = true;
        this.isDeleted = false;
    }

    public byte getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public byte getCredit() {
        return credit;
    }

    public void setCredit(byte credit) {
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
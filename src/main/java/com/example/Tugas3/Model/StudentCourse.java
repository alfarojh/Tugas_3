package com.example.Tugas3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StudentCourse {
    private final int id;   // ID Relasi antara Mahasiswa dan Mata Kuliah
    private String npm;     // NPM Mahasiswa
    private byte idCourse;  // ID Mata Kuliah
    private byte quiz1;     // Nilai kuis 1
    private byte quiz2;     // Nilai kuis 2
    private byte quiz3;     // Nilai kuis 3
    private byte quiz4;     // Nilai kuis 4
    private byte quiz5;     // Nilai kuis 5
    private byte exam1;     // Nilai Ujian Tengah Semester
    private byte exam2;     // Nilai Ujian Akhir Semester
    private boolean isDeleted;

    public StudentCourse(int id, String npm, byte idCourse) {
        this.id = id;
        this.npm = npm;
        this.idCourse = idCourse;
        this.quiz1 = -1;    // Nilai default -1 -> untuk menandakan bahwa nilai belum ada.
        this.quiz2 = -1;
        this.quiz3 = -1;
        this.quiz4 = -1;
        this.quiz5 = -1;
        this.exam1 = -1;
        this.exam2 = -1;
        this.isDeleted = false;
    }

    public int getId() {
        return id;
    }

    public String getNpm() {
        return npm.trim();
    }

    public void setNpm(String npm) {
        this.npm = npm.trim();
    }

    public byte getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(byte idCourse) {
        this.idCourse = idCourse;
    }

    public byte getQuiz1() {
        return quiz1;
    }

    public void setQuiz1(byte quiz1) {
        this.quiz1 = quiz1;
    }

    public byte getQuiz2() {
        return quiz2;
    }

    public void setQuiz2(byte quiz2) {
        this.quiz2 = quiz2;
    }

    public byte getQuiz3() {
        return quiz3;
    }

    public void setQuiz3(byte quiz3) {
        this.quiz3 = quiz3;
    }

    public byte getQuiz4() {
        return quiz4;
    }

    public void setQuiz4(byte quiz4) {
        this.quiz4 = quiz4;
    }

    public byte getQuiz5() {
        return quiz5;
    }

    public void setQuiz5(byte quiz5) {
        this.quiz5 = quiz5;
    }

    public byte getExam1() {
        return exam1;
    }

    public void setExam1(byte exam1) {
        this.exam1 = exam1;
    }

    public byte getExam2() {
        return exam2;
    }

    public void setExam2(byte exam2) {
        this.exam2 = exam2;
    }

    @JsonIgnore
    public boolean isExist() {
        return !isDeleted;
    }

    public void delete() {
        isDeleted = true;
    }
}

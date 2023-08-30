package com.example.Tugas3.model;

public class StudentCourse {
    private final long id;  // ID Relasi antara Mahasiswa dan Mata Kuliah
    private final String npm;     // NPM Mahasiswa
    private final long idCourse;  // ID Mata Kuliah
    private final int credit;     // Jumlah SKS
    private Byte quiz1;     // Nilai kuis 1
    private Byte quiz2;     // Nilai kuis 2
    private Byte quiz3;     // Nilai kuis 3
    private Byte quiz4;     // Nilai kuis 4
    private Byte quiz5;     // Nilai kuis 5
    private Byte exam1;     // Nilai Ujian Tengah Semester
    private Byte exam2;     // Nilai Ujian Akhir Semester
    private boolean active;

    public StudentCourse(long id, String npm, long idCourse, int credit) {
        this.id = id;
        this.npm = npm;
        this.idCourse = idCourse;
        this.credit = credit;
        this.active = true;
    }

    public long getId() {
        return id;
    }

    public String getNpm() {
        if (npm == null) return null;
        return npm.trim();
    }

    public long getIdCourse() {
        return idCourse;
    }

    public int getCredit() {
        return credit;
    }

    public Byte getQuiz1() {
        return quiz1;
    }

    public void setQuiz1(Byte quiz1) {
        this.quiz1 = quiz1;
    }

    public Byte getQuiz2() {
        return quiz2;
    }

    public void setQuiz2(Byte quiz2) {
        this.quiz2 = quiz2;
    }

    public Byte getQuiz3() {
        return quiz3;
    }

    public void setQuiz3(Byte quiz3) {
        this.quiz3 = quiz3;
    }

    public Byte getQuiz4() {
        return quiz4;
    }

    public void setQuiz4(Byte quiz4) {
        this.quiz4 = quiz4;
    }

    public Byte getQuiz5() {
        return quiz5;
    }

    public void setQuiz5(Byte quiz5) {
        this.quiz5 = quiz5;
    }

    public Byte getExam1() {
        return exam1;
    }

    public void setExam1(Byte exam1) {
        this.exam1 = exam1;
    }

    public Byte getExam2() {
        return exam2;
    }

    public void setExam2(Byte exam2) {
        this.exam2 = exam2;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

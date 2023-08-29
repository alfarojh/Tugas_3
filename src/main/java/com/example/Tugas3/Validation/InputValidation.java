package com.example.Tugas3.Validation;

public class InputValidation {
    private final ValueValidation valueValidation = new ValueValidation();
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Memeriksa apakah sebuah nama sesuai dengan kriteria berikut:
     * - Tidak boleh null atau kosong.
     * - Tidak boleh mengandung simbol.
     *
     * @param name  Nama yang akan divalidasi.
     * @return      True jika semua kriteria terpenuhi, dan false jika sebaliknya.
     */
    public boolean isNameValid(String name) {
        boolean isValid = valueValidation.isNotNullOrEmpty(name, "name") &&
                valueValidation.isNotSymbol(name, "name");
        message = valueValidation.getMessage();
        return isValid;
    }

    /**
     * Memeriksa apakah suatu alamat sesuai dengan kriteria berikut:
     * - Tidak boleh null atau kosong.
     *
     * @param address   Alamat yang akan divalidasi.
     * @return          True jika semua kriteria terpenuhi, dan false jika sebaliknya.
     */
    public boolean isAddressValid(String address) {
        boolean isValid = valueValidation.isNotNullOrEmpty(address, "address");
        message = valueValidation.getMessage();
        return isValid;
    }

    /**
     * Memeriksa apakah jenis kelamin sesuai dengan kriteria berikut:
     * - Tidak boleh null atau kosong.
     *
     * @param gender    Jenis kelamin yang akan divalidasi.
     * @return          True jika semua kriteria terpenuhi, dan false jika sebaliknya.
     */
    public boolean isGenderValid(String gender) {
        boolean isValid = valueValidation.isNotNullOrEmpty(gender, "gender");
        message = valueValidation.getMessage();
        return isValid;
    }

    /**
     * Memeriksa apakah nomor telepon sesuai dengan kriteria berikut:
     * - Tidak boleh null atau kosong.
     * - Harus berupa bilangan bulat.
     * - Panjang karakter harus berada diantaran 10 sampai 13
     *
     * @param phoneNumber   Nomor telepon yang akan divalidasi.
     * @return              True jika semua kriteria terpenuhi, dan false jika sebaliknya.
     */
    public boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = valueValidation.isNotNullOrEmpty(phoneNumber, "phoneNumber") &&
                valueValidation.isDigit(phoneNumber, "phoneNumber") &&
                valueValidation.isLengthValid(phoneNumber, "phoneNumber", 10, 13);
        message = valueValidation.getMessage();
        return isValid;
    }

    /**
     * Memeriksa apakah jumlah SKS sesuai dengan kriteria berikut:
     * - Tidak boleh 0 atau nol.
     * - Harus berupa bilangan positif.
     *
     * @param credit    Jumlah SKS yang akan divalidasi.
     * @return          True jika semua kriteria terpenuhi, dan false jika sebaliknya.
     */
    public boolean isCreditValid(byte credit) {
        boolean isValid = valueValidation.isNotZero(credit, "credit") &&
                valueValidation.isPositive(credit, "credit");
        message = valueValidation.getMessage();
        return isValid;
    }

    /**
     * Memeriksa apakah nilai ujian sesuai dengan kriteria berikut:
     * - Harus berada dalam rentang -1 sampai 100, dengan -1 untuk menggambarkan jika ujian belum diberi nilai.
     *
     * @param grade     Nilai ujian yang akan divalidasi.
     * @return          True jika semua kriteria terpenuhi, dan false jika sebaliknya.
     */
    public boolean isGradeValid(int grade, String varName) {
        boolean isValid = valueValidation.inRange(grade, varName, -1, 100);
        message = valueValidation.getMessage();
        return isValid;
    }

    /**
     * Memeriksa apakah npm sesuai dengan kriteria berikut:
     * - Tidak boleh null atau kosong.
     * - Harus berupa bilangan bulat.
     * - Panjang karakter harus 10.
     *
     * @param npm   NPM yang akan divalidasi.
     * @return      True jika semua kriteria terpenuhi, dan false jika sebaliknya.
     */
    public boolean isNpmValid(String npm) {
        boolean isValid = valueValidation.isNotNullOrEmpty(npm, "npm") &&
                valueValidation.isDigit(npm, "npm") &&
                valueValidation.isLengthValid(npm, "npm", 10);
        message = valueValidation.getMessage();
        return isValid;
    }

    /**
     * Memeriksa apakah ID Jurusan sesuai dengan kriteria berikut:
     * - Tidak boleh 0 atau nol.
     * - Harus berupa bilangan positif.
     *
     * @param idMajor   ID Jurusan yang akan divalidasi.
     * @return          True jika semua kriteria terpenuhi, dan false jika sebaliknya.
     */
    public boolean isIdMajorValid(byte idMajor) {
        boolean isValid = valueValidation.isNotZero(idMajor, "idMajor") &&
                valueValidation.isPositive(idMajor, "idMajor");
        message = valueValidation.getMessage();
        return isValid;
    }

    /**
     * Memeriksa apakah ID Mata Kuliah sesuai dengan kriteria berikut:
     * - Tidak boleh 0 atau nol.
     * - Harus berupa bilangan positif.
     *
     * @param idCourse  ID Mata Kuliah yang akan divalidasi.
     * @return          True jika semua kriteria terpenuhi, dan false jika sebaliknya.
     */
    public boolean isIdCourseValid(byte idCourse) {
        boolean isValid = valueValidation.isNotZero(idCourse, "idCourse") &&
                valueValidation.isPositive(idCourse, "idCourse");
        message = valueValidation.getMessage();
        return isValid;
    }

    /**
     * Memeriksa apakah ID dari relasi antara mahasiswa dan mata kuliah sesuai dengan kriteria berikut:
     * - Tidak boleh 0 atau nol.
     * - Harus berupa bilangan positif.
     *
     * @param idGrade   ID dari relasi antara mahasiswa dan mata kuliah yang akan divalidasi.
     * @return          True jika semua kriteria terpenuhi, dan false jika sebaliknya.
     */
    public boolean isIdStudentCourseValid(int idGrade) {
        boolean isValid = valueValidation.isNotZero(idGrade, "idGrade") &&
                valueValidation.isPositive(idGrade, "idGrade");
        message = valueValidation.getMessage();
        return isValid;
    }
}

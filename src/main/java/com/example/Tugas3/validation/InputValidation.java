package com.example.Tugas3.validation;

public class InputValidation {
    private final ValueValidation valueValidation = new ValueValidation();

    public String getMessage() {
        return valueValidation.getMessage();
    }

    public void setMessage(String message) {
        this.valueValidation.setMessage(message);
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
        return valueValidation.isNotNullOrEmpty(name, "name") &&
                valueValidation.isNotSymbol(name, "name");
    }

    /**
     * Memeriksa apakah suatu alamat sesuai dengan kriteria berikut:
     * - Tidak boleh null atau kosong.
     *
     * @param address   Alamat yang akan divalidasi.
     * @return          True jika semua kriteria terpenuhi, dan false jika sebaliknya.
     */
    public boolean isAddressValid(String address) {
        return valueValidation.isNotNullOrEmpty(address, "address");
    }

    /**
     * Memeriksa apakah jenis kelamin sesuai dengan kriteria berikut:
     * - Tidak boleh null atau kosong.
     *
     * @param gender    Jenis kelamin yang akan divalidasi.
     * @return          True jika semua kriteria terpenuhi, dan false jika sebaliknya.
     */
    public boolean isGenderValid(String gender) {
        return valueValidation.isNotNullOrEmpty(gender, "gender");
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
        return valueValidation.isNotNullOrEmpty(phoneNumber, "phoneNumber") &&
                valueValidation.isDigit(phoneNumber, "phoneNumber") &&
                valueValidation.isLengthValid(phoneNumber, "phoneNumber", 10, 13);
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
        return valueValidation.isNotZero(credit, "credit") &&
                valueValidation.isPositive(credit, "credit");
    }

    /**
     * Memeriksa apakah nilai ujian sesuai dengan kriteria berikut:
     * - Harus berada dalam rentang -1 sampai 100, dengan -1 untuk menggambarkan jika ujian belum diberi nilai.
     *
     * @param grade     Nilai ujian yang akan divalidasi.
     * @return          True jika semua kriteria terpenuhi, dan false jika sebaliknya.
     */
    public boolean isGradeValid(Byte grade, String varName) {
        if (grade == null) {
            return true;
        } else {
            return valueValidation.inRange(grade.intValue(), varName, 0, 100);
        }
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
        return valueValidation.isNotNullOrEmpty(npm, "npm") &&
                valueValidation.isDigit(npm, "npm") &&
                valueValidation.isLengthValid(npm, "npm", 10);
    }

    /**
     * Memeriksa apakah ID Jurusan sesuai dengan kriteria berikut:
     * - Tidak boleh 0 atau nol.
     * - Harus berupa bilangan positif.
     *
     * @param idMajor   ID Jurusan yang akan divalidasi.
     * @return          True jika semua kriteria terpenuhi, dan false jika sebaliknya.
     */
    public boolean isIdMajorValid(long idMajor) {
        return valueValidation.isNotZero(idMajor, "idMajor") &&
                valueValidation.isPositive(idMajor, "idMajor");
    }

    /**
     * Memeriksa apakah ID Mata Kuliah sesuai dengan kriteria berikut:
     * - Tidak boleh 0 atau nol.
     * - Harus berupa bilangan positif.
     *
     * @param idCourse  ID Mata Kuliah yang akan divalidasi.
     * @return          True jika semua kriteria terpenuhi, dan false jika sebaliknya.
     */
    public boolean isIdCourseValid(long idCourse) {
        return valueValidation.isNotZero(idCourse, "idCourse") &&
                valueValidation.isPositive(idCourse, "idCourse");
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
        return valueValidation.isNotZero(idGrade, "idGrade") &&
                valueValidation.isPositive(idGrade, "idGrade");
    }
}

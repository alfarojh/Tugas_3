package com.example.Tugas3.Validation;

public class ValueValidation {
    private String message;

    public String getMessage() {
        return message;
    }

    /**
     * Memeriksa apakah input tidak null atau kosong.
     *
     * @param input   Input yang akan divalidasi.
     * @param nameVar Nama variabel yang sedang diperiksa.
     * @return True jika input tidak null atau kosong, dan false jika sebaliknya.
     */
    public boolean isNotNullOrEmpty(String input, String nameVar) {
        if (input == null) {
            message = "The value of `" + nameVar + "` cannot be null!";
            return false;
        } else if (input.trim().isEmpty()) {
            message = "The value of `" + nameVar + "` cannot be empty!";
            return false;
        }
        return true;
    }

    /**
     * Memeriksa apakah input memiliki nilai 0 atau nol.
     *
     * @param input     Input yang akan divalidasi.
     * @param nameVar   Nama variabel yang sedang diperiksa.
     * @return          True jika input bukan bernilai 0 atau nol,
     *                  dan false jika sebaliknya.
     */
    public boolean isNotZero(int input, String nameVar) {
        if (input == 0) {
            message = "The value of `" + nameVar + "` cannot be zero!";
            return false;
        }
        return true;
    }

    /**
     * Memeriksa apakah input memiliki nilai positif.
     *
     * @param input     Input yang akan divalidasi.
     * @param nameVar   Nama variabel yang sedang diperiksa.
     * @return          True jika input memiliki nilai positif,
     *                  dan false jika sebaliknya.
     */
    public boolean isPositive(int input, String nameVar) {
        if (input < 0) {
            message = "The value of `" + nameVar + "` cannot be negative!";
            return false;
        }
        return true;
    }

    /**
     * Memeriksa apakah input berada dalam rentang yang ditentukan.
     *
     * @param input     Input yang akan divalidasi.
     * @param nameVar   Nama variabel yang sedang diperiksa.
     * @param minRange  Batas minimal rentang.
     * @param maxRange  Batas maksimal rentang.
     * @return          Mengembalikan true jika input berada dalam rentang,
     *                  dan false jika input di luar rentang.
     */
    public boolean inRange(int input, String nameVar, int minRange, int maxRange) {
        if (input < minRange || input > maxRange) {
            message = "The value of `" + nameVar + "` is out of range. " +
                    "The value must be between " + minRange + " and " + maxRange + "!";
            return false;
        }
        return true;
    }

    /**
     * Memeriksa apakah input mengandung simbol.
     *
     * @param input     Input yang akan divalidasi.
     * @param nameVar   Nama variabel yang sedang diperiksa.
     * @return          True jika input tidak mengandung simbol,
     *                  dan false jika input mengandung simbol.
     */

    public boolean isNotSymbol(String input, String nameVar) {
        if (!input.matches("[a-zA-Z0-9\\s]+")) {
            message = "The value of `" + nameVar + "` cannot contain symbols!";
            return false;
        }
        return true;
    }

    /**
     * Memeriksa apakah input merupakan bilangan bulat.
     *
     * @param input     Input yang akan divalidasi.
     * @param nameVar   Nama variabel yang sedang diperiksa.
     * @return          True jika input adalah bilangan bulat,
     *                  dan false jika input bukan bilangan bulat.
     */

    public boolean isDigit(String input, String nameVar) {
        if (!input.matches("\\d+")) {
            message = "The value of `" + nameVar + "` must be a number.";
            return false;
        }
        return true;
    }


    /**
     * Memeriksa apakah panjang karakter input berada di antara batas minimal dan batas maksimal yang ditentukan.
     *
     * @param input     Input yang akan divalidasi.
     * @param nameVar   Nama variabel yang sedang diperiksa.
     * @param minLength Batas minimal panjang karakter.
     * @param maxLength Batas maksimal panjang karakter.
     * @return          True jika panjang karakter input berada dalam rentang batas,
     *                  dan false jika panjang karakter input di luar rentang batas.
     */

    public boolean isLengthValid(String input, String nameVar, int minLength, int maxLength) {
        if (input.length() < minLength || input.length() > maxLength) {
            message = "The value of `" + nameVar + "` must be a number between " + minLength + " and " + maxLength + ".";
            return false;
        }
        return true;
    }

    /**
     * Memeriksa apakah panjang karakter input melebihi batas yang ditentukan.
     *
     * @param input     Input yang akan divalidasi.
     * @param nameVar   Nama variabel yang sedang diperiksa.
     * @param length    Batas panjang karakter.
     * @return          True jika panjang karakter input berada dalam batas,
     *                  dan false jika panjang karakter input melebihi batas.
     */
    public boolean isLengthValid(String input, String nameVar, int length) {
        if (input.length() != length) {
            message = "The value of `" + nameVar + "` must be " + length + " character.";
            return false;
        }
        return true;
    }
}

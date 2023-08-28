package com.example.Tugas3.Validation;

public class ValueValidation {
    private String message;

    public String getMessage() {
        return message;
    }

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

    public boolean isNotZero(int input, String nameVar) {
        if (input == 0) {
            message = "The value of `" + nameVar + "` cannot be zero!";
            return false;
        }
        return true;
    }

    public boolean isPositive(int input, String nameVar) {
        if (input < 0) {
            message = "The value of `" + nameVar + "` cannot be negative!";
            return false;
        }
        return true;
    }

    public boolean inRange(int input, String nameVar, int minRange, int maxRange) {
        if (input < minRange || input > maxRange) {
            message = "The value of `" + nameVar + "` is out of range." +
                    "The value must be between " + minRange + " and " + maxRange + "!";
            return false;
        }
        return true;
    }

    public boolean isNotSymbol(String input, String nameVar) {
        if (!input.matches("[a-zA-Z0-9\\s]+")) {
            message = "The value of `" + nameVar + "` cannot contain symbols!";
            return false;
        }
        return true;
    }

    public boolean isDigit(String input, String nameVar) {
        if (!input.matches("\\d+")) {
            message = "The value of `" + nameVar + "` must be a number.";
            return false;
        }
        return true;
    }

    public boolean isLengthValid(String input, String nameVar, int minLength, int maxLength) {
        if (input.length() < minLength || input.length() > maxLength) {
            message = "The value of `" + nameVar + "` must be a number between " + minLength + " and " + maxLength + ".";
            return false;
        }
        return true;
    }

    public boolean isLengthValid(String input, String nameVar, int length) {
        if (input.length() != length) {
            message = "The value of `" + nameVar + "` must be " + length + " character.";
            return false;
        }
        return true;
    }
}

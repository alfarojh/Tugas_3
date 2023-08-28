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

    private void errorMessage(String message) {
        System.out.println("\u001B[31m" + message + "\u001B[0m");
    }

    public boolean isNameValid(String name) {
        boolean isValid = valueValidation.isNotNullOrEmpty(name, "name") &&
                valueValidation.isNotSymbol(name, "name");
        message = valueValidation.getMessage();
        return isValid;
    }

    public boolean isAddressValid(String address) {
        boolean isValid = valueValidation.isNotNullOrEmpty(address, "address");
        message = valueValidation.getMessage();
        return isValid;
    }

    public boolean isGenderValid(String gender) {
        boolean isValid = valueValidation.isNotNullOrEmpty(gender, "gender");
        message = valueValidation.getMessage();
        return isValid;
    }

    public boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = valueValidation.isNotNullOrEmpty(phoneNumber, "phoneNumber") &&
                valueValidation.isDigit(phoneNumber, "phoneNumber") &&
                valueValidation.isLengthValid(phoneNumber, "phoneNumber", 10, 13);
        message = valueValidation.getMessage();
        return isValid;
    }

    public boolean isCreditValid(byte credit) {
        boolean isValid = valueValidation.isNotZero(credit, "credit");
        message = valueValidation.getMessage();
        return isValid;
    }

    public boolean isGradeValid(int grade, String varName) {
        boolean isValid = valueValidation.inRange(grade, varName, -1, 100);
        message = valueValidation.getMessage();
        return isValid;
    }

    public boolean isNpmValid(String npm) {
        boolean isValid = valueValidation.isNotNullOrEmpty(npm, "npm") &&
                valueValidation.isDigit(npm, "npm") &&
                valueValidation.isLengthValid(npm, "npm", 10);
        message = valueValidation.getMessage();
        return isValid;
    }

    public boolean isIdMajorValid(byte idMajor) {
        boolean isValid = valueValidation.isNotZero(idMajor, "idMajor") &&
                valueValidation.isPositive(idMajor, "idMajor");
        message = valueValidation.getMessage();
        return isValid;
    }

    public boolean isIdCourseValid(byte idCourse) {
        boolean isValid = valueValidation.isNotZero(idCourse, "idCourse") &&
                valueValidation.isPositive(idCourse, "idCourse");
        message = valueValidation.getMessage();
        return isValid;
    }

    public boolean isIdStudentCourseValid(int idGrade) {
        boolean isValid = valueValidation.isNotZero(idGrade, "idGrade") &&
                valueValidation.isPositive(idGrade, "idGrade");
        message = valueValidation.getMessage();
        return isValid;
    }
}

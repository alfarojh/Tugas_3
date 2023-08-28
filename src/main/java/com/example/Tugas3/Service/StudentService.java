package com.example.Tugas3.Service;

import com.example.Tugas3.Model.Student;
import com.example.Tugas3.Validation.InputValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private InputValidation inputValidation = new InputValidation();
    private List<Student> students = new ArrayList<>();
    @Autowired
    private MajorService majorService;
    private String message;

    public StudentService() {
        seed();
    }

    public String getMessage() {
        return message;
    }

    public boolean isNpmExist(String npm) {
        if (getIndexByNpm(npm) < 0) {
            message = inputValidation.getMessage();
            return false;
        } else return true;
    }

    public Student getStudentByIndex(int index) {
        return students.get(index);
    }

    public int getStudentSize() {
        return students.size();
    }

    //============================================== CRUD =================================================

    public boolean add(String name, byte idMajor, String gender, String address, String phoneNumber) {
        if (inputValidation.isNameValid(name) &&
                isIDMajorExist(idMajor) &&
                inputValidation.isGenderValid(gender) &&
                inputValidation.isAddressValid(address) &&
                inputValidation.isPhoneNumberValid(phoneNumber)) {
            students.add(new Student(getNewNPM(idMajor), idMajor, name, gender, address, phoneNumber));
            message = "Student added successfully.";
            return true;
        }
        message = inputValidation.getMessage();
        return false;
    }

    public boolean update(String npm, byte idMajor, String name, String gender, String address, String phoneNumber, boolean status) {
        if (inputValidation.isNpmValid(npm) &&
                inputValidation.isNameValid(name) &&
                inputValidation.isGenderValid(gender) &&
                inputValidation.isAddressValid(address) &&
                inputValidation.isPhoneNumberValid(phoneNumber) &&
                isNpmExist(npm)) {
            students.get(getIndexByNpm(npm)).setName(name);
            students.get(getIndexByNpm(npm)).setIdMajor(idMajor);
            students.get(getIndexByNpm(npm)).setGender(gender);
            students.get(getIndexByNpm(npm)).setAddress(address);
            students.get(getIndexByNpm(npm)).setPhoneNumber(phoneNumber);
            students.get(getIndexByNpm(npm)).setActive(status);
            message = "Student with NPM `" + npm + "` updated successfully.";
            return true;
        }
        message = inputValidation.getMessage();
        return false;
    }

    public boolean delete(String npm) {
        if (inputValidation.isNpmValid(npm) && isNpmExist(npm)) {
            students.get(getIndexByNpm(npm)).delete();
            message = "Student with NPM `" + npm + "` deleted successfully.";
            return true;
        }
        message = inputValidation.getMessage();
        return false;
    }

    public List<Student> getStudents() {
        return students.stream()
                .filter(Student::isExist)
                .collect(Collectors.toList());
    }

    public Student getStudentByNpm(String npm) {
        if (inputValidation.isNpmValid(npm) && isNpmExist(npm)) {
            message = "Student NPM Found.";
            return students.get(getIndexByNpm(npm));
        }
        message = inputValidation.getMessage();
        return null;
    }

    //============================================== CRUD =================================================

    private String getNewNPM(byte idMajor) {
        String year = "2012";
        int count = 1;
        for (int indexStudent = students.size() - 1; indexStudent >= 0 ; indexStudent--) {
            if (students.get(indexStudent).getNpm().substring(0, 6).equals(year + String.format("%02d", idMajor))) {
                count = Integer.parseInt(students.get(indexStudent).getNpm().substring(6, 10)) + 1;
            }
        }
        return year + String.format("%02d", idMajor) + String.format("%04d", count);
    }

    private int getIndexByNpm(String npm) {
        for (int indexStudent = 0; indexStudent < students.size(); indexStudent++) {
            if (students.get(indexStudent).getNpm().equals(npm) &&
                    students.get(indexStudent).isExist()) {
                return indexStudent;
            }
        }
        inputValidation.setMessage("NPM not found.");
        return -1;
    }

    private boolean isIDMajorExist(byte idMajor) {
        boolean isExist = majorService.isIdExist(idMajor);
        inputValidation.setMessage(majorService.getMessage());
        return isExist;
    }

    private void seed() {
        byte idMajor = (byte) 1;
        students.add(new Student(
                getNewNPM(idMajor),
                idMajor,
                "Budi",
                "Laki-laki",
                "Bandung",
                "08231238438"));
        students.add(new Student(
                getNewNPM(idMajor),
                idMajor,
                "Santi",
                "Perempuan",
                "Bandung",
                "085748372934"));
    }
}

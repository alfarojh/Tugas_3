package com.example.Tugas3.Service;

import com.example.Tugas3.Model.Major;
import com.example.Tugas3.Validation.InputValidation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MajorService {
    private InputValidation inputValidation = new InputValidation();
    private List<Major> majors = new ArrayList<>();
    private String message;

    public MajorService() {
        seed();
    }

    public String getMessage() {
        return message;
    }

    public boolean isIdExist(byte id) {
        if (getIndexById(id) < 0) {
            message = inputValidation.getMessage();
            return false;
        }
        return true;
    }

    public byte getMajorsSize() {
        return (byte) majors.size();
    }

    //============================================== CRUD =================================================

    public boolean add(Major major) {
        if (inputValidation.isNameValid(major.getName())) {
            majors.add(new Major(getNewIndex(), major.getName().trim()));
            message = "Major added successfully.";
            return true;
        }
        message = inputValidation.getMessage();
        return false;
    }

    public boolean update(byte id, Major major) {
        if (inputValidation.isIdMajorValid(id) &&
                inputValidation.isNameValid(major.getName()) &&
                isIdExist(id)) {
            majors.get(getIndexById(id)).setName(major.getName());
            message = "Major with ID `" + id + "` updated successfully.";
            return true;
        }
        message = inputValidation.getMessage();
        return false;
    }

    public boolean delete(byte id) {
        if (inputValidation.isIdMajorValid(id) &&
                isIdExist(id)) {
            majors.get(getIndexById(id)).delete();
            message = "Major with ID `" + id + "` deleted successfully.";
            return true;
        }
        message = inputValidation.getMessage();
        return false;
    }

    public List<Major> getMajors() {
        return majors.stream()
                .filter(Major::isExist)
                .collect(Collectors.toList());
    }

    public Major getMajorById(byte id) {
        if (inputValidation.isIdMajorValid(id) && isIdExist(id)) {
            message = "Major ID Found.";
            return majors.get(getIndexById(id));
        }
        message = inputValidation.getMessage();
        return null;
    }

    //============================================== CRUD =================================================

    private byte getNewIndex() {
        return (byte) (majors.size() + 1);
    }

    private int getIndexById(byte id) {
        if (id > 0 && id <= majors.size() && majors.get(id-1).isExist()) return id - 1;
        inputValidation.setMessage("Major id not found.");
        return -1;
    }

    private void seed() {
        majors.add(new Major(getNewIndex(),"Teknik Informatika"));
        majors.add(new Major(getNewIndex(),"Sistem Informasi"));
    }
}

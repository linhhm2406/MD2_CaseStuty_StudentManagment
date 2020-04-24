package dataManagement;

import entity.*;
import java.util.ArrayList;

public class StudentManagement {
    public ArrayList<Student> studentList = new ArrayList<>();
    public ArrayList<Student> searchingList = new ArrayList<>();


    public StudentManagement() {
    }

    public Student getStudentById(int id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == id) {
                return studentList.get(i);
            }
        }
        return null;
    }
}
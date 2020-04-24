package controller;

import view.*;
import dataManagement.*;
import entity.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeInformationController {
    private ChangeInformationDisplay changeInformationDisplay;
    private ManagementDisplayController managementDisplayController;
    private StudentManagement studentManagement = new StudentManagement();
    private DataEdit dataEdit = new DataEdit();
    private Student studentToDisplay;

    public Student getStudentToDisplay() {
        return studentToDisplay;
    }

    public void setStudentToDisplay(Student studentToDisplay) {
        this.studentToDisplay = studentToDisplay;
    }

    public ChangeInformationController(Student studentToDisplay) {
        changeInformationDisplay = new ChangeInformationDisplay();
        managementDisplayController = new ManagementDisplayController("Main");
        setStudentToDisplay(studentToDisplay);
        takeInforToInputField();
        okButtonFunction();
        cancelButtonFunction();
    }

    public void takeInforToInputField() {
        Student studentToChange = getStudentToDisplay();
        changeInformationDisplay.getIdInputField().setText(String.valueOf(studentToChange.getId()));
        changeInformationDisplay.getFirstNameInputField().setText(studentToChange.getFirstName());
        changeInformationDisplay.getLastNameInputField().setText(studentToChange.getLastName());
        changeInformationDisplay.getGenderInputField().setText(studentToChange.getGender());
        changeInformationDisplay.getGradeInputField().setText(String.valueOf(studentToChange.getGrade()));
        changeInformationDisplay.getDobInputField().setText(studentToChange.getDateOfBirth());
        changeInformationDisplay.getPhoneNoInputField().setText(studentToChange.getPhoneNumber());
    }

    private void cancelButtonFunction() {
        changeInformationDisplay.getCancelButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                changeInformationDisplay.dispose();
            }
        });
    }

    private void okButtonFunction() {
        changeInformationDisplay.getOkButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Student studentAfterChange = buildStudentFollowInputField();
                dataEdit.changeInformationStudentInSQL(studentAfterChange);
                reloadDataOfMainTable();
                changeInformationDisplay.dispose();
            }
        });
    }

    private void reloadDataOfMainTable() {
        ManagementDisplayController.defaultTableModel.setRowCount(0);
        loadDataToMainTable();
    }

    private void loadDataToMainTable() {
        managementDisplayController.setDataForLoadToTable(dataEdit.importDataToMainArray(studentManagement.studentList));
        for (Student student : managementDisplayController.getDataForLoadToTable()) {
            ManagementDisplayController.defaultTableModel.addRow(new Object[]{student.getId(), student.getFirstName(),
                    student.getLastName(), student.getGender(), student.getGrade(),
                    student.getDateOfBirth(), student.getPhoneNumber()});
        }
    }

    public Student buildStudentFollowInputField() {
        return new Student(Integer.parseInt(changeInformationDisplay.getIdInputField().getText()),
                changeInformationDisplay.getFirstNameInputField().getText(),
                changeInformationDisplay.getLastNameInputField().getText(),
                changeInformationDisplay.getGenderInputField().getText(),
                Double.parseDouble(changeInformationDisplay.getGradeInputField().getText()),
                changeInformationDisplay.getDobInputField().getText(),
                changeInformationDisplay.getPhoneNoInputField().getText());
    }
}
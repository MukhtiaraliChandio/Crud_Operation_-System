/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.beans.Student;
import java.util.List;

/**
 *
 * @author MUKHTIAR ALI CHANDIO
 */
public interface StudentDao {
 
 
 
  public Boolean  deleteStudentdataByid(int id);
  
 
 public List<Student> getAllStudents();

public List<String> getSelectedhobbies();
  



 public void addStudent(String firstName, String lastName, String fatherName, String dateOfbirth, String surname, String cnic, String contactNumber, String email, String gender, List<String> selectedHobbies, String selectedprovince, String selectedcountry, String address);

 
 public  Student getStudentdataByid(int id);
 
 public String getGenderFromDatabase(int studentId);

 //public void updateStudentdata(int stdId, String firstName, String lastName, String surname, String cnic, String contactNumber, String email, String dateOfbirth, String selectedProvince, String selectedCountry, String address, String hobbies, String gender);

    public int updateStudentdata(int stdId, String firstName, String lastName, String surname, String cnic, String contactNumber, String email, String dateOfbirth, String selectedProvince, String selectedCountry, String address, String hobbies, String gender);
 
 
 
}
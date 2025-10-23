/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jframes;

import StudentDaoImpl.StudentDaoImpl;
import com.beans.Student;
import com.dao.StudentDao;
import java.util.List;

/**
 *
 * @author MUKHTIAR ALI CHANDIO
 */
public class TestMain {
    
 
    public static void main(String args[]){
        
           StudentDao daoImpl = new StudentDaoImpl();
      List<Student> students = daoImpl.getAllStudents();

        for (Student s : students) {
            System.out.println("ID: " + s.getId());
            System.out.println("First Name: " + s.getFirstName());
            System.out.println("Last Name: " + s.getLastName());
            System.out.println("Surname: " + s.getSurname());
            System.out.println("CNIC: " + s.getCnic());
            System.out.println("Address: " + s.getAddress());
            System.out.println("Contact No: " + s.getContactNo());
            System.out.println("Email ID: " + s.getEmailId());
            System.out.println("---------------");
        }
    }
    
    
}
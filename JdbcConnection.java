/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author MUKHTIAR ALI CHANDIO
 */
public class JdbcConnection {
    
    
    
 public JdbcConnection(){
		 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud_desktop_java","root","");
			System.out.println("Connection Created");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
    private  Connection con;
    

    private static void init() throws Exception{

    	
    }// end of static init method .

	public  Connection getCon() {
		return con;
	}
	
	

	
	
	
    
    
    
    
    
}
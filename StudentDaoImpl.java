/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentDaoImpl;

import com.beans.Student;
import com.dao.StudentDao;
import com.jdbc.connection.JdbcConnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MUKHTIAR ALI CHANDIO
 */
public class StudentDaoImpl implements StudentDao{

   JdbcConnection  db=new JdbcConnection();
   
  @Override          
public void addStudent(String firstName, String lastName , String fatherName, String dateOfbirth,  String surname, String cnic, String contactNumber, String email, String gender, List<String> selectedHobbies, String selectedprovince, String selectedcountry,  String address){      
    Connection con = db.getCon(); // here is call con variable from db.getCon(). connection jdbc. 
    Student    std=new Student();
    String query="insert into student(first_name, last_name, father_name,  date_of_birth,  surname, cnic, contact_number, email_id, gender, hobbies, province, country, address) values ('"+firstName+"', '"+lastName+"', '"+fatherName+"',  '"+dateOfbirth+"',   '"+surname+"',  '"+cnic+"', '"+contactNumber+"', '"+email+"', '"+gender+"', '"+selectedHobbies+"', '"+selectedprovince+"', '"+selectedcountry+"',  '"+address+"')";

    
    System.out.println("Query:"+query);
    Statement st=null;
    int row=0;
    try{
        
    	st=con.createStatement();
        System.out.println();
        row=st.executeUpdate(query);
     
     
        if (row>0) 
                JOptionPane.showMessageDialog(null, row + "Record Saved");
        else  
                JOptionPane.showMessageDialog(null, row + "Not Saved");

           
        
        
       //return rows;  
                
    }catch(Exception e){
    	e.printStackTrace();
    }
      // return row;
	
    
    }
 
 
 
 
 
 
 
 
  @Override
public List<Student> getAllStudents(){
     
    Connection con = db.getCon(); // here is call con variable from db.getCon(). connection jdbc. 
     List<Student> stdlist = new ArrayList<>();
     
      //  Student stdData=null;
	String query = "select * from  student"; /// here is write sql query? 
            

        // Create table model

        // Add column names to model
      
      
       try {
           Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
      
        
          while (rs.next()) {
               
            Student stdData = new Student();
            stdData.setId(rs.getInt("std_id"));
            stdData.setFirstName(rs.getString("first_name"));
            stdData.setLastName(rs.getString("last_name"));
            stdData.setFatherName(rs.getString("father_name"));
            stdData.setDateOfbirth(rs.getString("date_of_birth"));
            stdData.setSurname(rs.getString("surname"));
            stdData.setCnic(rs.getString("cnic"));
            stdData.setContactNo(rs.getString("contact_number"));
            stdData.setEmailId(rs.getString("email_id"));
            stdData.setGender(rs.getString("gender"));
            stdData.setHobbies(rs.getString("hobbies"));
            stdData.setProvince(rs.getString("province"));
            stdData.setCountry(rs.getString("country"));
            stdData.setAddress(rs.getString("address"));
            stdlist.add(stdData); // << Add to list
                        
            }

        // Set model to table
        
             
           
           
       } catch (Exception e) {
           e.printStackTrace();
       }
  
     
     
     
  return stdlist;
        
 }// end of List<Student> getAllStudents()

 @Override
public  Student getStudentdataByid(int id){
       
 Connection con = db.getCon(); // here is call con variable from db.getCon(). connection jdbc. 

  Student std=new Student();
  
  
      Student stdData=null;
       String  query = "select * from  student where std_id="+id;            

        // Create table model

        // Add column names to model
      
      
       try {
           Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
      
        
          while (rs.next()) {
               
            stdData = new Student();
            stdData.setId(rs.getInt("std_id"));
            stdData.setFirstName(rs.getString("first_name"));
            stdData.setLastName(rs.getString("last_name"));
            stdData.setFatherName(rs.getString("father_name"));
            stdData.setDateOfbirth(rs.getString("date_of_birth"));
            stdData.setSurname(rs.getString("surname"));
            stdData.setCnic(rs.getString("cnic"));
            stdData.setContactNo(rs.getString("contact_number"));
            stdData.setEmailId(rs.getString("email_id"));
            stdData.setGender(rs.getString("gender"));
            stdData.setHobbies(rs.getString("hobbies"));
            stdData.setProvince(rs.getString("province"));
            stdData.setCountry(rs.getString("country"));
            stdData.setAddress(rs.getString("address"));
            
            //stdlist.add(stdData); // << Add to list
                        
            }

        // Set model to table
        
             
           
           
       } catch (Exception e) {
           e.printStackTrace();
       }
  
     
     
     
 // return stdlist;
    
    
    
    
   return stdData;
    
    
      
} 
 @Override
public String getGenderFromDatabase(int studentId){
    
     Connection con = db.getCon(); // here is call con variable from db.getCon(). connection jdbc. 
     String gender = null;

     String query = "SELECT gender FROM student WHERE std_id = '"+studentId+"'";
    
     try{
         
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(query);
         if(rs.next()) {
            gender = rs.getString("gender");
         }
     }catch(Exception e){
         
         e.printStackTrace();
         
     }
    
    
     return gender;
    
    
    
}


  @Override
public List<String> getSelectedhobbies(){
       
  Connection con = db.getCon(); // here is call con variable from db.getCon(). connection jdbc. 

  List<String> hobbies = new ArrayList<>();
  String sql = "SELECT hobbies FROM student";
  
  try{
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql);
       while(rs.next()) {
          
           hobbies.add(rs.getString("hobbies"));
            
        }
    
  }catch(Exception e){
      e.printStackTrace();
      
  }
 
    return hobbies;
    
    
}



 @Override
public Boolean  deleteStudentdataByid(int id) {
    
    Connection con = db.getCon();
    String query="delete from student where   std_id="+id;
    System.out.println(query);

	Statement st=null;
	int row=0;
	try{
		
             st=con.createStatement();
	     row=st.executeUpdate(query);
	     if (row>0) 
                JOptionPane.showMessageDialog(null, row + "Data Deleted");
              else  
                JOptionPane.showMessageDialog(null, row + "Data  Not Deleted");

		}catch(Exception e){
			e.printStackTrace();

 }// end of d
       return true;
		
	
}//delete Student data Byid.


   @Override
   public int updateStudentdata(int stdId, String firstName, String lastName, String surname, String cnic, String contactNumber, String email, String dateOfbirth, String selectedProvince, String selectedCountry, String address, String hobbies, String gender){

     Connection con =db.getCon();
    //Student stdData=new Student();
     String query="Update student Set first_name='"+firstName+"', last_name='"+lastName+"', surname='"+surname+"',   cnic='"+cnic+"', contact_number='"+contactNumber+"', email_id='"+email+"'  where std_id='"+stdId+"' "; 
     System.out.println("UpdateQuery:"+query);
     Statement st=null;
     int row=0;
     try{
	  st=con.createStatement();
	  // System.out.println(stdData);
	  row=st.executeUpdate(query);
	 if (row>0) 
                JOptionPane.showMessageDialog(null, row + "Data Update");
              else  
                JOptionPane.showMessageDialog(null, row + "Data  Not Update");

	
	}catch(Exception e){
	  e.printStackTrace();
	}
	return row;
    
    
    
    


}


}
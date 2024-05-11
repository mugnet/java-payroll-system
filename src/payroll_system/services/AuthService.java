package payroll_system.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPasswordField;

import payroll_system.Alert;
import payroll_system.Database;
import payroll_system.FormHelper;
import payroll_system.TextHelper;
import payroll_system.views.employee.DashboardEmployee;
import payroll_system.views.hr.DashboardHr;
import payroll_system.views.employee.DashboardEmployee;
public class AuthService extends Database {

public int currentUserId;
	
	
public int getCurrentUserId() {
	return currentUserId;
}


public void setCurrentUserId(int currentUserId) {
	this.currentUserId = currentUserId;
}


public void loginUser(String email,JPasswordField userPassword) { 
	
	Alert alert = new Alert();
	
	char[] passwordChars = userPassword.getPassword();
    String password = new String(passwordChars);
	
	
	try {
 
		String[] fields = {email,password};
		
		PreparedStatement statement = query("SELECT * FROM users WHERE email = ? AND password = ?",fields);
		ResultSet resultSet = statement.executeQuery();

      
     if (resultSet.next()) {
 
    	 
    	 this.setCurrentUserId(resultSet.getInt("id"));
    	 
    	  
    	 FormHelper form = new FormHelper(); 
		 form.hideActiveFrame(); 
		 
		 if(resultSet.getInt("user_type_id") == 2) {
			
			 FormHelper.show(new DashboardEmployee(resultSet.getInt("id")),true);
			 
		 }else if(resultSet.getInt("user_type_id") ==1) {
			 FormHelper.show(new DashboardHr(),true);
		 }else {
			 alert.setMessage("We could not found any details from your entry. Please try again!");
			 
		 }
    	 
    	   
    	 
         
     } else { 
 		
 		
    		alert.setMessage("Invalid username or password. Please try again!");
     		alert.danger();
 		
 		
         
     }
     
     dbClose(resultSet, statement); 
    
    
    
     
     
	}catch (SQLException e) {
        e.printStackTrace();
    }   
	 
}
}

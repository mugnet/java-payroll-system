package payroll_system.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPasswordField;

import payroll_system.Database;

public class AuthService extends Database {

public int loginUser(String email,JPasswordField userPassword) { 

	
	char[] passwordChars = userPassword.getPassword();
    String password = new String(passwordChars);
	
	
	try {
 
		String[] fields = {email,password};
		
		PreparedStatement statement = query("SELECT * FROM users WHERE email = ? AND password = ?",fields);
		ResultSet resultSet = statement.executeQuery();

      
     if (resultSet.next()) {
//    	 while (response.next()) { 
//             int id = response.getInt("id");
//             String name = response.getString("name"); 
//             System.out.println("ID: " + id + ", Name: " + name);
//         }
//    	 
     
    	 
    	 
    	 return resultSet.getInt("user_type_id");
         
     } else {
    	 System.out.println("Invalid username or password.");
  
         
     }
     
     resultSet.close();
     statement.close();
    
    
    
     
     
	}catch (SQLException e) {
        e.printStackTrace();
    }   
	
	return 0;
}
}
package payroll_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Database extends Base {
	 
		public Connection con;
	  public Database() {
	 		  
			
	        	this.init();
	  }
	  
	  

	  public void init() {
		
	    	 
try  {
	
	  	String JDBC_URL = config("DB_DRIVER") + config("DB_HOST") + ":" + config("DB_PORT") + "/"+ config("DB_NAME");
  		String USERNAME = config("DB_USERNAME");
  		String PASSWORD = config("DB_PASSWORD");
	
  		this.con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);        	
	    System.out.println("Connected to database!"); 
	            
	        } catch (SQLException e) {
	            System.err.println("Connection failed!");
	            e.printStackTrace();
	        } 
	
	  }
	 
	  public PreparedStatement query(String sql) {
			 
		  try  {
		     PreparedStatement statement = this.con.prepareStatement(sql);
		     
		    
		    return statement; 
		      
		     
		  } catch (SQLException e) {
	            System.err.println("Connection failed!");
	            e.printStackTrace();
	        }
		return null; 
		    
		  
	  }
	  
	  public PreparedStatement query(String sql, String[] fields) {
		 
		  try  {
		     PreparedStatement statement = this.con.prepareStatement(sql);
		     
		     for(int i=0; i < fields.length;i++) { 
		    	  statement.setString(i+1, fields[i].trim()); 
		     }
		     
		    return statement; 
		      
		     
		  } catch (SQLException e) {
	            System.err.println("Connection failed!");
	            e.printStackTrace();
	        }
		return null; 
		    
		  
	  }
	  
	  
	  public void dbClose(ResultSet resultSet, PreparedStatement statement) throws SQLException {
		  	resultSet.close();
		    statement.close();
	  }
	  
	 
 
	  
	  
}

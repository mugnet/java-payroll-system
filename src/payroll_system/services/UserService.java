package payroll_system.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import javax.swing.JTable;

import payroll_system.Alert;
import payroll_system.Database;
import payroll_system.TableHelper;

public class UserService extends TableHelper {

	private Database DB;
	private Alert alert;
	private String name;
	private String email;
	private String userType;
	private String password;
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private final String db_table = "users";

	private String[] headers = { "#", "Name","Email","User Type", "Registered At", "Updated At" };

	public UserService() {
		
		alert = new Alert();
		setHeaders(headers);
	}

	public JTable get() {
		
		getHeaders();
		setModel(getModel());

		try {
			DB = new Database();
			PreparedStatement statement = DB.query("SELECT users.*, user_types.name FROM " + this.db_table + " INNER JOIN user_types ON users.user_type_id = user_types.id");
			ResultSet resultSet = statement.executeQuery();
			 
			while (resultSet.next()) {
				 getModel().addRow(new Object[] {
						resultSet.getString("users.id"),
						resultSet.getString("users.name"),
						resultSet.getString("users.email"),
						resultSet.getString("user_types.name"),
						resultSet.getString("users.created_at"),
						resultSet.getString("users.updated_at")
				});
				  

			} 
 			

			DB.dbClose(resultSet, statement);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return config();

	}
	
	
	public Object show(Object id) {
		
		System.out.println("testing " + id);
		try {
			DB = new Database();
			PreparedStatement statement = DB.first("SELECT users.*, user_types.name FROM " + this.db_table + " INNER JOIN user_types ON users.user_type_id = user_types.id where id = ?",id);
			
			
			ResultSet resultSet = statement.executeQuery();
 			
			 if (resultSet.next()) { 
	            this.setName(resultSet.getString("name"));
	            this.setEmail(resultSet.getString("email"));
	            this.setUserType(resultSet.getString("user_type_id")); 
	         } else {
	            alert.setMessage("No data is found!");
	            alert.danger();
	         }

			DB.dbClose(resultSet, statement);
			
	 
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	 return null;

	}
	
	 

	public void create(String[] fields) {
		
		try {
			DB = new Database();
			PreparedStatement statement = DB.query("INSERT INTO " + this.db_table + " (name,email,user_type_id,password) VALUES (?,?,?,?)");
			
			statement.setString( 1, fields[0]);
			statement.setString( 2, fields[1]);
			statement.setInt( 3,  Integer.parseInt(fields[2]));
			statement.setString(4, fields[3]);
			
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				alert.setMessage("A new record has been inserted successfully.");
				alert.success();
			} else {
				alert.setMessage("Failed to insert the record.");
				alert.warning();

			}

			statement.close();
			DB.con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void update(String[] fields, Object idToUpdate) {
		try {
			
			Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
			DB = new Database();
			PreparedStatement statement = DB.update("UPDATE " + this.db_table + " SET name = ?, email = ?, user_type_id  = ? WHERE id = ?", fields,
					idToUpdate);
			int i;
			for(i = 0; i<=fields.length -1; i++) {
				statement.setString(i+1, fields[i]);
			}
			statement.setTimestamp(fields.length, timestamp);
			statement.setObject(fields.length + 1,idToUpdate);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				alert.setMessage("Record has been successfully updated.");
				alert.success();
			} else {
				alert.setMessage("Failed to update the record.");
				alert.warning();

			}

			statement.close();
			DB.con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void delete(Object idToUpdate) {
		try {
			alert.setMessage("Do want to delete the selected record?");
			int option = alert.question();
			
			if (option == 0) {
			DB = new Database();
			PreparedStatement statement = DB.delete("DELETE FROM " + this.db_table + "  WHERE id = ?", idToUpdate);
			statement.setObject(1, idToUpdate);
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				 
					alert.setMessage("Record has been successfully deleted.");
					alert.success();
				
			} else {
				alert.setMessage("Failed to update the record.");
				alert.warning();

			}

			statement.close();
			DB.con.close();
			}else {
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

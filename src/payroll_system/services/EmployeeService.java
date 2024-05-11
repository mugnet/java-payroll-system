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

public class EmployeeService extends TableHelper {

	private Database DB;
	private Alert alert;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String userType;
	private String password;
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	private int employeeId;
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private final String db_table = "employees";

	private String[] headers = { "#", "Last Name","First Name","Middle Name","Rate","Email","Sex","Department","Position", "Created At", "Updated At" };

	private String sql;
	
	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public EmployeeService() {
		
		alert = new Alert();
		setHeaders(headers);
		this.setSql("SELECT employees.*,positions.name,departments.name, users.email FROM " + this.db_table
				+ " INNER JOIN users ON employees.user_id = users.id" 
				+ " INNER JOIN positions ON employees.position_id = positions.id" 
				+ " INNER JOIN departments ON employees.department_id = departments.id");
		
//		this.setEmployeeId(1);
		
	}

	public JTable get() {
		
		getHeaders();
		setModel(getModel());

		try {
			DB = new Database();
			PreparedStatement statement = DB.query(this.getSql());
			ResultSet resultSet = statement.executeQuery();
			 
			
			while (resultSet.next()) {
				 getModel().addRow(new Object[] {
						resultSet.getInt("employees.id"),
						resultSet.getString("employees.last_name"),
						resultSet.getString("employees.first_name"),
						resultSet.getString("employees.middle_name"),
						resultSet.getString("employees.rate"),
						resultSet.getString("users.email"),
						resultSet.getString("employees.sex"),
						resultSet.getString("positions.name"),
						resultSet.getString("departments.name"),
						resultSet.getString("employees.created_at"),
						resultSet.getString("employees.updated_at")
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
			PreparedStatement statement = DB.first(this.getSql() + " where employees.id = ?",id);
			
			
			ResultSet resultSet = statement.executeQuery();
 			
			 if (resultSet.next()) { 
	             
//	            this.setUserType(resultSet.getString("user_type_id")); 
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
	
	 

	public void create(int position_id,int department_id, int user_id,String fname, String mname, String lname, Object sex,float rate) {
		
		try {
			DB = new Database();
			PreparedStatement statement = DB.query("INSERT INTO " + this.db_table + " (position_id,department_id,user_id,first_name,middle_name,last_name,sex,created_at,rate) VALUES (?,?,?,?,?,?,?,?,?)");
			
			statement.setInt( 1, position_id);
			statement.setInt( 2, department_id);
			statement.setInt( 3, user_id);
			statement.setString( 4, fname);
			statement.setString( 5, mname);
			statement.setString( 6, lname);
			statement.setObject( 7, sex);
			statement.setTimestamp(8, DB.timeStamp());
			statement.setFloat(9, rate);
			 
			
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				alert.setMessage("A new record has been created successfully.");
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

	public void update(Object where_id, int position_id,int department_id, int user_id,String fname, String mname, String lname, Object sex, float rate) {
		try {
			
			Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
			DB = new Database(); 
			PreparedStatement statement = DB.query("UPDATE " + this.db_table + " SET position_id=?,department_id=?,user_id=?,first_name=?,middle_name=?,last_name=?,sex=?,updated_at=?, rate=? where id=?");
			
			statement.setInt( 1, position_id);
			statement.setInt( 2, department_id);
			statement.setInt( 3, user_id);
			statement.setString( 4, fname);
			statement.setString( 5, mname);
			statement.setString( 6, lname);
			statement.setObject( 7, sex);
			statement.setTimestamp(8, DB.timeStamp());
			statement.setObject(9,where_id);
			statement.setFloat(10, rate);
			 
			
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				alert.setMessage("Employee details has been updated.");
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
	
	
	public void setAttendance(String timein, String timeout) {
		try {
			DB = new Database();
			PreparedStatement statement = DB.query(timein != null ? "INSERT INTO attendances (employee_id, time_in) VALUES (?,?)" : "INSERT INTO attendances (employee_id, time_out) VALUES (?,?)" );
			
			statement.setInt( 1, getEmployeeId());
			if(timein == null) {
				statement.setString(2, timeout);;
			}else {
				statement.setString(2, timein);;
			}
			
			
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				alert.setMessage("Your attendance has been recorded successfully.");
				alert.success();
			} else {
				alert.setMessage("Failed to record your attendance.");
				alert.warning();

			}

			statement.close();
			DB.con.close();
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

	 
 
}

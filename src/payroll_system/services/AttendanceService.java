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

public class AttendanceService extends TableHelper {

	private Database DB;
	private Alert alert;
	private String name;   
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	private int employeeId;
	
	
 

	private final String db_table = "attendances";

	private String[] headers = { "#", "Name","Time-in","Time-out" };

	private String sql;
	
	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public AttendanceService() {
		
		alert = new Alert();
		setHeaders(headers);
		this.setSql("SELECT attendances.*,employees.first_name,employees.middle_name, employees.last_name FROM " + this.db_table
				+ " INNER JOIN employees ON attendances.employee_id = employees.id order by id desc");
		
//		this.setEmployeeId(1);
		
	}

	public JTable get() {
		
		getHeaders();
		setModel(getModel());

		try {
			DB = new Database();
			PreparedStatement statement = DB.query(this.getSql());
			ResultSet resultSet = statement.executeQuery();
			 
			int index = 0;
			while (resultSet.next()) {
				 getModel().addRow(new Object[] {
						index+=1,
						resultSet.getString("employees.last_name") + ", "+ resultSet.getString("employees.first_name")+" "+resultSet.getString("employees.middle_name"),
						resultSet.getString("attendances.time_in"),
						resultSet.getString("attendances.time_out")
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
	
	 

	public void create(int position_id,int department_id, int user_id,String fname, String mname, String lname, Object sex) {
		
		try {
			DB = new Database();
			PreparedStatement statement = DB.query("INSERT INTO " + this.db_table + " (position_id,department_id,user_id,first_name,middle_name,last_name,sex,created_at) VALUES (?,?,?,?,?,?,?,?)");
			
			statement.setInt( 1, position_id);
			statement.setInt( 2, department_id);
			statement.setInt( 3, user_id);
			statement.setString( 4, fname);
			statement.setString( 5, mname);
			statement.setString( 6, lname);
			statement.setObject( 7, sex);
			statement.setTimestamp(8, DB.timeStamp());
			 
			
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

	public void update(Object where_id, int position_id,int department_id, int user_id,String fname, String mname, String lname, Object sex) {
		try {
			
			Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
			DB = new Database(); 
			PreparedStatement statement = DB.query("UPDATE " + this.db_table + " SET position_id=?,department_id=?,user_id=?,first_name=?,middle_name=?,last_name=?,sex=?,updated_at=? where id=?");
			
			statement.setInt( 1, position_id);
			statement.setInt( 2, department_id);
			statement.setInt( 3, user_id);
			statement.setString( 4, fname);
			statement.setString( 5, mname);
			statement.setString( 6, lname);
			statement.setObject( 7, sex);
			statement.setTimestamp(8, DB.timeStamp());
			statement.setObject(9,where_id);
			 
			
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
	
	
	

	
	
	
 
	 
 
}

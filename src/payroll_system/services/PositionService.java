package payroll_system.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTable;

import payroll_system.Alert;
import payroll_system.Database;
import payroll_system.TableHelper;

public class PositionService extends TableHelper {

	private Database DB;
	private Alert alert;
	private String name;
	 
	 
	
	 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private final String db_table = "positions";

	private String[] headers = { "#", "Name", "Created At", "Updated At" };

	public PositionService() {
		
		alert = new Alert();
		setHeaders(headers);
	}

	
	
	public void getData(JComboBox<String> cbo, String sql) {
		 
		try {
			DB = new Database();
			PreparedStatement statement = DB.query(sql);
			ResultSet resultSet = statement.executeQuery();
			 
			 Map<Integer, String> dataMap = new HashMap<>();
			 
 
            while (resultSet.next()) {
            	int id = resultSet.getInt("id");
                String value = resultSet.getString("name"); 
                dataMap.put(id, value);
                
            }
            
            for (Map.Entry<Integer, String> entry : dataMap.entrySet()) {
                cbo.addItem(entry.getValue());
            }
                        
            DB.dbClose(resultSet, statement);
 			   
			 
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	 
		 
	}
	
	
	public JTable get() {
		
		getHeaders();
		setModel(getModel());

		try {
			DB = new Database();
			PreparedStatement statement = DB.query("SELECT * FROM " + this.db_table);
			ResultSet resultSet = statement.executeQuery();
			 
			while (resultSet.next()) {
				 getModel().addRow(new Object[] {
						resultSet.getString("id"),
						resultSet.getString("name"),
						resultSet.getString("created_at"),
						resultSet.getString("updated_at")
				});
				  

			} 
 			

			DB.dbClose(resultSet, statement);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return config();

	}
	
	
	public Object show(Object id) {
		
	 
		try {
			DB = new Database();
			PreparedStatement statement = DB.first("SELECT * FROM " + this.db_table + " where id = ?",id);
			
			
			ResultSet resultSet = statement.executeQuery();
 			
			 if (resultSet.next()) { 
	            this.setName(resultSet.getString("name")); 
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
			PreparedStatement statement = DB.create("INSERT INTO " + this.db_table + " (name) VALUES (?)", fields);

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
			DB = new Database();
			PreparedStatement statement = DB.update("UPDATE " + this.db_table + " SET name = ? WHERE id = ?", fields,
					idToUpdate);
			
			statement.setString(1, fields[0]);
			statement.setObject(2,idToUpdate);

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
}

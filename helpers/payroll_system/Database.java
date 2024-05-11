package payroll_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Database extends Base {

	public Connection con;

	public Database() {

		this.init();
	}

	public void init() {

		try {

			String JDBC_URL = config("DB_DRIVER") + config("DB_HOST") + ":" + config("DB_PORT") + "/"
					+ config("DB_NAME");
			String USERNAME = config("DB_USERNAME");
			String PASSWORD = config("DB_PASSWORD");

			this.con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
//			System.out.println("Connected to database!");

		} catch (SQLException e) {
			System.err.println("Connection failed!");
			e.printStackTrace();
		}

	}

	public PreparedStatement query(String sql) {

		try {
			PreparedStatement statement = this.con.prepareStatement(sql);

			return statement;

		} catch (SQLException e) {
			System.err.println("Connection failed!");
			e.printStackTrace();
		}
		return null;

	}
	
	public PreparedStatement select(String sql) {
		return this.query( "select " + sql);
	}

	
	
	public PreparedStatement query(String sql, String[] fields) {

		try {
			PreparedStatement statement = this.con.prepareStatement(sql);

			for (int i = 0; i < fields.length; i++) {
				statement.setString(i + 1, fields[i].trim());
			}

			return statement;

		} catch (SQLException e) {
			System.err.println("Connection failed!");
			e.printStackTrace();
		}
		return null;

	}

	public PreparedStatement create(String sql, String[] fields) { 

		String insertQuery = sql;

		try {

			PreparedStatement statement = this.con.prepareStatement(insertQuery);

			for (int i = 0; i <= fields.length - 1; i++) {
				 
				statement.setString(i + 1, fields[i]);
				
			}

			// Executing the query
			// int rowsInserted = statement.executeUpdate();
			// if (rowsInserted > 0) {
			// alert.setMessage("A new record has been inserted successfully.");
			// alert.success();
			// } else {
			// alert.setMessage("Failed to insert the record.");
			// alert.success();
			//
			//
			// }

			return statement;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public PreparedStatement update(String sql, String[] fields, Object idToUpdate) {

		try {

			PreparedStatement statement = this.con.prepareStatement(sql);
			 
 
			return statement;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public PreparedStatement delete(String sql, Object idToUpdate) {

		try {

			PreparedStatement statement = this.con.prepareStatement(sql);
			

			return statement;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public PreparedStatement first(String sql, Object id) {

		try {

			PreparedStatement statement = this.con.prepareStatement(sql);
			statement.setObject(1, id);

			return statement;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	

	public void dbClose(ResultSet resultSet, PreparedStatement statement) throws SQLException {
		resultSet.close();
		statement.close();
	}
	
	public Timestamp timeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		return timestamp;
	}

}

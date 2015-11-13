package Tutorials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLTutorial {
	String connectionString = "jdbc:mysql://localhost:3306/SQLTutorial";
	String user = "root";
	String password = "";
	Connection m_Connection;

	SQLTutorial() throws SQLException {
		m_Connection = DriverManager.getConnection(connectionString, user, password);
	}

	public static void main(String[] args) throws Exception {
		SQLTutorial sql = new SQLTutorial();
		
		String query = "SELECT * FROM StudentRecord";
		// Simple execution
		ResultSet result = sql.executeQuery(query);
		
		// reading and printing from ResultSet
		// you must call .next method to point to the first row before iterating.
		while(result.next()){
			
			String studentId = result.getString(1); // index based retrieval index starts from 1, gets the first column of the row.
			String studentFirstName = result.getString("stud_first_name"); // column name based retrieval, gets the stud_first_name column of the row.
			String studentDOB = result.getString("stud_date_of_birth");
			
			System.out.println("ID: " + studentId +", First Name: " + studentFirstName + ", Date of Birth: " + studentDOB);
		}
		
		// Simple Update
		// inserting a row to the table
		query = "INSERT INTO studentrecord VALUES('89887','Nicolas','Cage','1979-06-24','158, Side Lake, Kerry','MSc. MultiMedia','1','Ireland')";
		sql.executeUpdate(query);
		
	}

	private ResultSet executeQuery(String query) throws SQLException {
		Statement statement = m_Connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		return resultSet;
	}

	private void executeUpdate(String query) throws SQLException {
		Statement statement = m_Connection.createStatement();
		statement.executeUpdate(query);
	}

}

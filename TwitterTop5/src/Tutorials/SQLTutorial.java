/**
 *  Tutorial class
 *  
 *  Assumes you have a database set up as "SQLTutorial" locally. and it has a table "StudentRecord".
 *  The structure of the table should be:
 *  Column name				Data type		Constraints
	student_id				VARCHAR(7)		NOT NULL PRIMARY KEY
	stud_first_name 		VARCHAR(15)		NOT NULL
	stud_last_name			VARCHAR(15)		NOT NULL
	stud_date_of_birth		DATE			NOT NULL
	stud_address			VARCHAR(50)		NOT NULL
	stud_program			VARCHAR(20)		NOT NULL
	stud_marital_status		INT(2)			NOT NULL
	stud_country			VARCHAR(20)		NOT NULL
	
	Populate this table with some data.
	SQL query to do all this is given below:	

    -- create a table
    CREATE TABLE IF NOT EXISTS studentrecord (
       student_id VARCHAR(7),
       stud_first_name VARCHAR(15) NOT NULL,
       stud_last_name VARCHAR(15) NOT NULL,
       stud_date_of_birth DATE NOT NULL,
       stud_address VARCHAR(50) NOT NULL,
       stud_program VARCHAR(20) NOT NULL,
       stud_marital_status INT(2) NOT NULL,
       stud_country VARCHAR(20) NOT NULL,
       PRIMARY KEY (student_id)
    )
    -- insert a sample data
    INSERT INTO studentrecord VALUES(
    '88889','Albukori','Zaman Khan','1969-07-08',
    '4-5, Dead Wood Street 5, 12000 Sintok, Kedah','MSc. IT',
    '1','Malaysia');     

    INSERT INTO studentrecord VALUES(
    '87990','Haslina','Mahathir','1970-11-12',
    '345, New Smart Village, 17100 Nilai, N. Sembilan','MSc. ICT',
    '2','Malaysia');    

    INSERT INTO studentrecord VALUES(
    '79678','Mohammed','Fajr','1975-04-20',
    'Pearl Apt, Level 10, Al-Hijr, 45200 Abu Dhabi','MSc. Expert System',
    '2','UEA');     

    INSERT INTO studentrecord VALUES(
    '88799','Mustar','Mohd Dali','1979-06-24',
    '345, Side Village, Kerian, 12300 Jawa Barat','MSc. MultiMedia',
    '1','Indonesia');     

    INSERT INTO studentrecord VALUES(
    '78998','Satkorn','Chengmo','1968-01-26',
    '34 Uptown Street #4, Tech Park, 78100 Bangkok','MSc. IT',
    '2','Thailand');

 */


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

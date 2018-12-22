package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class MySQLTableCreation {

	public static void main(String[] args) {
		try {
			System.out.println("connecting to " + MySQLDBUtil.URL);
			Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
			Connection conn = DriverManager.getConnection(MySQLDBUtil.URL);
			
			if (conn == null) {
				return;
			}
		
			Statement stmt = conn.createStatement();
			
			String sql = "DROP TABLE IF EXISTS places";
			stmt.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS users";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE places ("
					+ "place_Id VARCHAR(255) NOT NULL ,"
					+ "placeName VARCHAR(255) NOT NULL ,"
					+ "lon VARCHAR(255) NOT NULL ,"
					+ "lat VARCHAR(255) NOT NULL ,"
					+ "PRIMARY KEY (place_Id)"
					+ ")";
			
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE users ("
					+ "user_id VARCHAR(255) NOT NULL,"
					+ "password VARCHAR(255) NOT NULL,"
					+ "first_name VARCHAR(255),"
					+ "last_name VARCHAR(255),"
					+ "PRIMARY KEY (user_id)"
					+ ")";
			
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO places VALUES ('1111', 'somewhere', '12.345', '67.890')";
			stmt.executeUpdate(sql);
			
			System.out.println("Import done successfully");			
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
